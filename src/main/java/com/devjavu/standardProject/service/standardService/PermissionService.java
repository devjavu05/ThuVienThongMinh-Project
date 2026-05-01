package com.devjavu.standardProject.service.standardService;

import com.devjavu.standardProject.dto.request.standardRequest.PermissionRequest;
import com.devjavu.standardProject.dto.response.standardResponse.PermissionResponse;
import com.devjavu.standardProject.entity.standardEntity.Permission;
import com.devjavu.standardProject.mapper.standardMapper.PermissionMapper;
import com.devjavu.standardProject.repository.standardRepo.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse createPermission(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        return  permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    public List<PermissionResponse> getPermissions(){
        return  permissionRepository.findAll()
                .stream().
                map(permissionMapper::toPermissionResponse).toList();

    }
    public void deletePermission(String name){
        permissionRepository.deleteById(name);
    }
}
