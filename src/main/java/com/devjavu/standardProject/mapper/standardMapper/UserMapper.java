package com.devjavu.standardProject.mapper.standardMapper;

import com.devjavu.standardProject.dto.request.standardRequest.UserCreationRequest;
import com.devjavu.standardProject.dto.request.standardRequest.UserUpdateRequest;
import com.devjavu.standardProject.dto.response.standardResponse.UserResponse;
import com.devjavu.standardProject.entity.standardEntity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles",ignore = true)
    void updateUser(@MappingTarget User user , UserUpdateRequest request);


}
