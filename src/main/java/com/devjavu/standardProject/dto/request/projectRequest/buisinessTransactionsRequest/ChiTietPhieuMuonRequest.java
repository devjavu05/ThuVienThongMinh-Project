package com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTietPhieuMuonRequest {
    LocalDate returnDate;
    String status;
    String phieuMuonId;
    String cuonSachBarcode;
}
