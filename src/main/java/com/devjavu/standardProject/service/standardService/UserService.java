package com.devjavu.standardProject.service.standardService;

import com.devjavu.standardProject.dto.request.standardRequest.UserCreationRequest;
import com.devjavu.standardProject.dto.request.standardRequest.UserUpdateRequest;
import com.devjavu.standardProject.dto.response.standardResponse.UserResponse;
import com.devjavu.standardProject.entity.standardEntity.Role;
import com.devjavu.standardProject.entity.standardEntity.User;
import com.devjavu.standardProject.enums.StandardRoles;
import com.devjavu.standardProject.exception.AppException;
import com.devjavu.standardProject.exception.ErrorCode;
import com.devjavu.standardProject.mapper.standardMapper.UserMapper;
import com.devjavu.standardProject.repository.standardRepo.RoleRepository;
import com.devjavu.standardProject.repository.standardRepo.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    public UserResponse createUser(UserCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw  new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus("ACTIVE");
        user.setFailedLoginAttempts(0);
        user.setLockedUntil(null);
        String[] INIT_ROLES ={StandardRoles.USER.name()};
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(List.of(INIT_ROLES)));
        user.setRoles(roles);
        return  userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getUser(){
        return userRepository.findAll()
                .stream().map(userMapper::toUserResponse).toList();
    }
    public UserResponse updateUser(String username, UserUpdateRequest request){
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(user,request);
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(request.getRoles()));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        if (user.getStatus() == null) {
            user.setStatus("ACTIVE");
        }
        return  userMapper.toUserResponse(userRepository.save(user));

    }
    public User getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return  user ;
    }
}
