package com.devjavu.standardProject.mapper.projectMapper.userProfilesMapper;

import com.devjavu.standardProject.dto.request.projectRequest.userProfilesRequest.NhanVienCreationRequest;
import com.devjavu.standardProject.dto.request.standardRequest.UserCreationRequest;
import com.devjavu.standardProject.dto.response.projectResponse.userProfileResponse.DocGiaResponse;
import com.devjavu.standardProject.dto.response.projectResponse.userProfileResponse.NhanVienResponse;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.NhanVien;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NhanVienMapper {

    NhanVien toNhanVien(NhanVienCreationRequest request);

    @Mapping(target = "username",source = "user.username")
    NhanVienResponse toNhanVienResponse(NhanVien nhanVien);

    UserCreationRequest toUserCreationRequest(NhanVienCreationRequest request);

}
