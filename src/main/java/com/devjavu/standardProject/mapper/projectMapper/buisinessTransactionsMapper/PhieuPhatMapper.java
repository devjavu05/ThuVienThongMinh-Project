package com.devjavu.standardProject.mapper.projectMapper.buisinessTransactionsMapper;

import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.PhieuPhatRequest;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuPhatResponse;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuPhat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhieuPhatMapper {
    @Mapping(target = "phieuMuon", ignore = true)
    @Mapping(target = "cuonSach", ignore = true)
    PhieuPhat toPhieuPhat(PhieuPhatRequest request);

    @Mapping(target = "phieuMuonId", source = "phieuMuon.id")
    @Mapping(target = "cuonSachBarcode", source = "cuonSach.barcode")
    @Mapping(target = "bookTitle", source = "cuonSach.dauSach.title")
    PhieuPhatResponse toPhieuPhatResponse(PhieuPhat phieuPhat);
}
