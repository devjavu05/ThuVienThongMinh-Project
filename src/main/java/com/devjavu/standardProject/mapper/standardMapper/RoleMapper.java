package com.devjavu.standardProject.mapper.standardMapper;

import com.devjavu.standardProject.dto.request.standardRequest.RoleRequest;
import com.devjavu.standardProject.dto.response.standardResponse.RoleResponse;
import com.devjavu.standardProject.entity.standardEntity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
