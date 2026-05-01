package com.devjavu.standardProject.mapper.projectMapper.buisinessTransactionsMapper;

import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.PhieuMuonRequest;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuMuonResponse;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuMuon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhieuMuonMapper {
    @Mapping(target = "nguoiMuon",ignore = true)
    @Mapping(target = "nhanVien",ignore = true)
    PhieuMuon toPhieuMuon(PhieuMuonRequest request);

    @Mapping(target = "fullName",source = "nguoiMuon.fullName")
    @Mapping(target = "email",source = "nguoiMuon.email")
    @Mapping(target = "creater",source = "nhanVien.fullName")
    @Mapping(target = "createrNumber",source = "nhanVien.phoneNumber")
    PhieuMuonResponse toPhieuMuonResponse(PhieuMuon phieuMuon);
}
