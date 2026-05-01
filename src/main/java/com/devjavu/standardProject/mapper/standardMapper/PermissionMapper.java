package com.devjavu.standardProject.mapper.standardMapper;

import com.devjavu.standardProject.dto.request.standardRequest.PermissionRequest;
import com.devjavu.standardProject.dto.response.standardResponse.PermissionResponse;
import com.devjavu.standardProject.entity.standardEntity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);

}
