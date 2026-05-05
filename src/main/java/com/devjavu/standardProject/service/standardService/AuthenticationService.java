package com.devjavu.standardProject.service.standardService;

import com.devjavu.standardProject.dto.request.standardRequest.AuthenticationRequest;
import com.devjavu.standardProject.dto.request.standardRequest.ForgotPasswordRequest;
import com.devjavu.standardProject.dto.response.standardResponse.AuthenticationResponse;
import com.devjavu.standardProject.dto.response.standardResponse.LogoutResponse;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import com.devjavu.standardProject.entity.standardEntity.InvalidatedToken;
import com.devjavu.standardProject.entity.standardEntity.User;
import com.devjavu.standardProject.enums.ProjectRoles;
import com.devjavu.standardProject.exception.AppException;
import com.devjavu.standardProject.exception.ErrorCode;
import com.devjavu.standardProject.mapper.standardMapper.UserMapper;
import com.devjavu.standardProject.repository.projectRepo.userProfileRepo.DocGiaRepository;
import com.devjavu.standardProject.repository.standardRepo.InvalidatedTokenRepository;
import com.devjavu.standardProject.repository.standardRepo.UserRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import static com.nimbusds.jose.JWSAlgorithm.HS512;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {
    static final int MAX_FAILED_ATTEMPTS = 5;
    static final int LOCK_DURATION_MINUTES = 15;

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    DocGiaRepository docGiaRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    @Value("${app.jwt.signerKey}")
    @NonFinal
    String SIGNER_KEY;

    public AuthenticationResponse loginUser(AuthenticationRequest request) {
        if (!StringUtils.hasText(request.getUsername()) || !StringUtils.hasText(request.getPassword())) {
            throw new AppException(ErrorCode.LOGIN_REQUIRED_FIELDS);
        }

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        unlockIfExpired(user);
        if (isLocked(user)) {
            throw new AppException(ErrorCode.ACCOUNT_LOCKED);
        }

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            registerFailedAttempt(user);
            throw new AppException(ErrorCode.FAIL_ACCOUNT);
        }

        clearLoginFailures(user);
        String businessRole = resolveBusinessRole(user);
        return AuthenticationResponse.builder()
                .authenticated(true)
                .token(generateToken(user))
                .username(user.getUsername())
                .role(businessRole)
                .redirectPath(resolveRedirectPath(businessRole))
                .message("Đăng nhập thành công")
                .build();
    }

    public String forgotPassword(ForgotPasswordRequest request) {
        if (!StringUtils.hasText(request.getEmail())) {
            throw new AppException(ErrorCode.FORGOT_PASSWORD_EMAIL_REQUIRED);
        }

        DocGia docGia = docGiaRepository.findByEmail(request.getEmail());
        if (docGia == null) {
            throw new AppException(ErrorCode.EMAIL_NOT_FOUND);
        }

        return "Yêu cầu khôi phục mật khẩu đã được ghi nhận. Vui lòng kiểm tra email của bạn.";
    }

    public LogoutResponse logout(String authorizationHeader) {
        String token = extractToken(authorizationHeader);
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            if (StringUtils.hasText(jwtId) && expirationTime != null) {
                invalidatedTokenRepository.save(InvalidatedToken.builder()
                        .id(jwtId)
                        .expiryTime(LocalDateTime.ofInstant(expirationTime.toInstant(), ZoneId.systemDefault()))
                        .build());
            }
        } catch (ParseException e) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return LogoutResponse.builder()
                .loggedOut(true)
                .redirectPath("/login")
                .message("Đăng xuất thành công")
                .build();
    }

    public String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(UUID.randomUUID().toString())
                .subject(user.getUsername())
                .issuer("devjavu.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", buildScope(user))
                .build();
        Payload payload = jwtClaimsSet.toPayload();
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        } catch (JOSEException e) {
            log.error("Can not create token", e);
            throw new RuntimeException(e);
        }
        return jwsObject.serialize();
    }

    String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                }
            });
        }
        return stringJoiner.toString();
    }

    void unlockIfExpired(User user) {
        if ("LOCKED".equalsIgnoreCase(user.getStatus())
                && user.getLockedUntil() != null
                && user.getLockedUntil().isBefore(LocalDateTime.now())) {
            clearLoginFailures(user);
        }
    }

    boolean isLocked(User user) {
        return "LOCKED".equalsIgnoreCase(user.getStatus())
                && user.getLockedUntil() != null
                && user.getLockedUntil().isAfter(LocalDateTime.now());
    }

    void registerFailedAttempt(User user) {
        int attempts = user.getFailedLoginAttempts() + 1;
        user.setFailedLoginAttempts(attempts);
        if (attempts >= MAX_FAILED_ATTEMPTS) {
            user.setStatus("LOCKED");
            user.setLockedUntil(LocalDateTime.now().plusMinutes(LOCK_DURATION_MINUTES));
        }
        userRepository.save(user);
    }

    void clearLoginFailures(User user) {
        user.setFailedLoginAttempts(0);
        user.setLockedUntil(null);
        user.setStatus("ACTIVE");
        userRepository.save(user);
    }

    String resolveBusinessRole(User user) {
        if (CollectionUtils.isEmpty(user.getRoles())) {
            return ProjectRoles.DOC_GIA.name();
        }
        boolean isChuThuVien = user.getRoles().stream().anyMatch(role -> ProjectRoles.CHU_THU_VIEN.name().equals(role.getName()));
        if (isChuThuVien) {
            return ProjectRoles.CHU_THU_VIEN.name();
        }
        boolean isNhanVien = user.getRoles().stream().anyMatch(role -> ProjectRoles.NHAN_VIEN.name().equals(role.getName()));
        if (isNhanVien) {
            return ProjectRoles.NHAN_VIEN.name();
        }
        return ProjectRoles.DOC_GIA.name();
    }

    String resolveRedirectPath(String businessRole) {
        return switch (businessRole) {
            case "CHU_THU_VIEN", "NHAN_VIEN" -> "/workspace";
            default -> "/reader/books";
        };
    }

    String extractToken(String authorizationHeader) {
        if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return authorizationHeader.substring(7);
    }
}
