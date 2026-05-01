package com.devjavu.standardProject.service.projectService;

import com.devjavu.standardProject.dto.request.projectRequest.userProfilesRequest.NhanVienCreationRequest;
import com.devjavu.standardProject.dto.request.standardRequest.UserCreationRequest;
import com.devjavu.standardProject.dto.response.projectResponse.userProfileResponse.NhanVienResponse;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.NhanVien;
import com.devjavu.standardProject.entity.standardEntity.Role;
import com.devjavu.standardProject.entity.standardEntity.User;
import com.devjavu.standardProject.enums.ProjectRoles;
import com.devjavu.standardProject.enums.StandardRoles;
import com.devjavu.standardProject.exception.AppException;
import com.devjavu.standardProject.exception.ErrorCode;
import com.devjavu.standardProject.mapper.projectMapper.userProfilesMapper.NhanVienMapper;
import com.devjavu.standardProject.mapper.standardMapper.UserMapper;
import com.devjavu.standardProject.repository.projectRepo.userProfileRepo.NhanVienRepository;
import com.devjavu.standardProject.repository.standardRepo.RoleRepository;
import com.devjavu.standardProject.repository.standardRepo.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class NhanVienService {
    UserRepository userRepository;
    NhanVienRepository nhanVienRepository;
    NhanVienMapper nhanVienMapper;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public NhanVienResponse createNhanVien(NhanVienCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw  new AppException(ErrorCode.USER_EXISTED);
        }
        UserCreationRequest request1 = nhanVienMapper.toUserCreationRequest(request);
        User user = userMapper.toUser(request1);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        String[] INIT_ROLES ={StandardRoles.USER.name(), ProjectRoles.DOC_GIA.name(),ProjectRoles.NHAN_VIEN.name()};
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(List.of(INIT_ROLES)));
        user.setRoles(roles);
        User savedUser = userRepository.save(user);

        NhanVien nhanVien = nhanVienMapper.toNhanVien(request);
        nhanVien.setUser(savedUser);
        return nhanVienMapper.toNhanVienResponse(nhanVienRepository.save(nhanVien));
    }
}
