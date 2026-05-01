package com.devjavu.standardProject.mapper.projectMapper.bookManagerMapper;

import com.devjavu.standardProject.dto.request.projectRequest.bookManagerRequest.CuonSachCreationRequest;
import com.devjavu.standardProject.dto.request.projectRequest.bookManagerRequest.CuonSachUpdateRequest;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.CuonSachResponse;
import com.devjavu.standardProject.entity.projectEntity.bookManager.CuonSach;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CuonSachMapper {
    @Mapping(target = "dauSach", source = "dauSach.id")
    CuonSachResponse toCuonSachResponse(CuonSach cuonSach);
    @Mapping(target = "dauSach",ignore = true)
    CuonSach toCuonSach(CuonSachCreationRequest request);
    void updateCuonSach(@MappingTarget CuonSach cuonSach, CuonSachUpdateRequest request);
}
