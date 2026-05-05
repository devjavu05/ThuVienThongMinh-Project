package com.devjavu.standardProject.mapper.projectMapper.bookManagerMapper;

import com.devjavu.standardProject.dto.request.projectRequest.bookManagerRequest.DauSachCreationRequest;
import com.devjavu.standardProject.dto.request.projectRequest.bookManagerRequest.DauSachUpdateRequest;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.DauSachResponse;
import com.devjavu.standardProject.entity.projectEntity.bookManager.DauSach;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DauSachMapper {
    @Mapping(target = "defaultLocation", expression = "java(com.devjavu.standardProject.configuration.ShelfLocationCatalog.buildBookLocation(dauSach.getFloorNumber(), dauSach.getCategory()))")
    DauSachResponse toDauSachResponse(DauSach dauSach);
    DauSach toDauSach(DauSachCreationRequest request);
    void updateDauSach(@MappingTarget DauSach dauSach, DauSachUpdateRequest request);
}
