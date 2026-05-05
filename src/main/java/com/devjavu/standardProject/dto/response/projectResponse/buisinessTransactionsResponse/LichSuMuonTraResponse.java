package com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class LichSuMuonTraResponse {
    List<LichSuMuonTraItemResponse> dangMuon;
    List<LichSuMuonTraItemResponse> daTra;
    List<PhieuPhatResponse> phieuPhat;
}
