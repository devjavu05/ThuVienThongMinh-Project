package com.devjavu.standardProject.mapper.projectMapper.buisinessTransactionsMapper;

import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.ChiTietPhieuMuonRequest;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.ChiTietPhieuMuonResponse;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.ChiTietPhieuMuon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChiTietPhieuMuonMapper {
    @Mapping(target = "phieuMuon", ignore = true)
    @Mapping(target = "cuonSach", ignore = true)
    ChiTietPhieuMuon toChiTietPhieuMuon(ChiTietPhieuMuonRequest request);

    @Mapping(target = "phieuMuonId", source = "phieuMuon.id")
    @Mapping(target = "cuonSachBarcode", source = "cuonSach.barcode")
    @Mapping(target = "bookTitle", source = "cuonSach.dauSach.title")
    ChiTietPhieuMuonResponse toChiTietPhieuMuonResponse(ChiTietPhieuMuon chiTietPhieuMuon);
}
