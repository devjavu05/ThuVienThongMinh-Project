package com.devjavu.standardProject.service.projectService;

import com.devjavu.standardProject.dto.request.projectRequest.userProfilesRequest.DocGiaCreationRequest;
import com.devjavu.standardProject.dto.request.projectRequest.userProfilesRequest.BalanceUpdateRequest;
import com.devjavu.standardProject.dto.request.standardRequest.UserCreationRequest;
import com.devjavu.standardProject.dto.response.projectResponse.userProfileResponse.DocGiaResponse;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import com.devjavu.standardProject.entity.standardEntity.Role;
import com.devjavu.standardProject.entity.standardEntity.User;
import com.devjavu.standardProject.enums.ProjectRoles;
import com.devjavu.standardProject.enums.StandardRoles;
import com.devjavu.standardProject.exception.AppException;
import com.devjavu.standardProject.exception.ErrorCode;
import com.devjavu.standardProject.mapper.projectMapper.userProfilesMapper.DocGiaMapper;
import com.devjavu.standardProject.mapper.standardMapper.UserMapper;
import com.devjavu.standardProject.repository.projectRepo.userProfileRepo.DocGiaRepository;
import com.devjavu.standardProject.repository.standardRepo.RoleRepository;
import com.devjavu.standardProject.repository.standardRepo.UserRepository;
import com.devjavu.standardProject.service.standardService.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class DocGiaService {
    DocGiaRepository docGiaRepository;
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    DocGiaMapper docGiaMapper;
    RoleRepository roleRepository;
    UserService userService;
    // tạo tài khoản cho độc giả
    public DocGiaResponse createDocgia(DocGiaCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw  new AppException(ErrorCode.USER_EXISTED);
        }
        UserCreationRequest request1 = docGiaMapper.toUserCreationRequest(request);
        User user = userMapper.toUser(request1);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        String[] INIT_ROLES ={StandardRoles.USER.name(),ProjectRoles.DOC_GIA.name()};
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(List.of(INIT_ROLES)));
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        var docgia = docGiaMapper.toDocGia(request);
        docgia.setCardType("STANDARD");
        docgia.setBalance(0);
        docgia.setUser(savedUser);
        var savedDocGia = docGiaRepository.save(docgia);
        return docGiaMapper.toDocGiaResponse(savedDocGia);
    }

    public DocGiaResponse getMyProfile() {
        return docGiaMapper.toDocGiaResponse(getCurrentDocGia());
    }

    public DocGiaResponse updateMyBalance(BalanceUpdateRequest request) {
        if (request == null || request.getAmount() <= 0) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        DocGia docGia = getCurrentDocGia();
        docGia.setBalance(docGia.getBalance() + request.getAmount());
        return docGiaMapper.toDocGiaResponse(docGiaRepository.save(docGia));
    }

    private DocGia getCurrentDocGia() {
        User user = userService.getMyInfo();
        DocGia docGia = docGiaRepository.findByUser(user);
        if (docGia == null || !StringUtils.hasText(docGia.getId())) {
            throw new AppException(ErrorCode.NOT_FOUND_DOCGIA);
        }
        return docGia;
    }

}
