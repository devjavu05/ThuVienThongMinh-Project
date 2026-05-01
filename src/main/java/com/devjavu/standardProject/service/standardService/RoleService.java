package com.devjavu.standardProject.service.standardService;

import com.devjavu.standardProject.dto.request.standardRequest.RoleRequest;
import com.devjavu.standardProject.dto.response.standardResponse.RoleResponse;
import com.devjavu.standardProject.entity.standardEntity.Role;
import com.devjavu.standardProject.exception.AppException;
import com.devjavu.standardProject.exception.ErrorCode;
import com.devjavu.standardProject.mapper.standardMapper.RoleMapper;
import com.devjavu.standardProject.repository.standardRepo.PermissionRepository;
import com.devjavu.standardProject.repository.standardRepo.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponse createRole(RoleRequest request){
        Role role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }
    public List<RoleResponse> getRole(){
        return roleRepository.findAll().stream()
                .map(roleMapper::toRoleResponse).toList();
    }
    @Transactional
    public  void deleteRole(String role){
        roleRepository.findById(role).orElseThrow(()->new AppException(ErrorCode.INVALID_ROLE));
        roleRepository.deleteById(role);
    }
}
