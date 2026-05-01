package com.devjavu.standardProject.mapper.projectMapper.userProfilesMapper;

import com.devjavu.standardProject.dto.request.projectRequest.userProfilesRequest.DocGiaCreationRequest;
import com.devjavu.standardProject.dto.request.standardRequest.UserCreationRequest;
import com.devjavu.standardProject.dto.response.projectResponse.userProfileResponse.DocGiaResponse;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocGiaMapper {
    DocGia toDocGia(DocGiaCreationRequest request);

    @Mapping(target = "username",source = "user.username")
    DocGiaResponse toDocGiaResponse(DocGia docGia);

    UserCreationRequest toUserCreationRequest(DocGiaCreationRequest request);
}
