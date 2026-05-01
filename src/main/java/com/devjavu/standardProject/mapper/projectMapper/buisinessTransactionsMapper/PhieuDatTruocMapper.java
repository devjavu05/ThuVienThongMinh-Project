package com.devjavu.standardProject.mapper.projectMapper.buisinessTransactionsMapper;

import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.PhieuDatTruocRequest;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuDatTruocResponse;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuDatTruoc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhieuDatTruocMapper {
    @Mapping(target = "docGia", ignore = true)
    @Mapping(target = "dauSach", ignore = true)
    PhieuDatTruoc toPhieuDatTruoc(PhieuDatTruocRequest request);

    @Mapping(target = "fullName", source = "docGia.fullName")
    @Mapping(target = "email", source = "docGia.email")
    @Mapping(target = "dauSachId", source = "dauSach.id")
    @Mapping(target = "title", source = "dauSach.title")
    PhieuDatTruocResponse toPhieuDatTruocResponse(PhieuDatTruoc phieuDatTruoc);
}
