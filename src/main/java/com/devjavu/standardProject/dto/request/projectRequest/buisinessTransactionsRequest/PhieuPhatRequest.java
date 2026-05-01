package com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhieuPhatRequest {
    double amount;
    String reason;
    boolean paid;
    String phieuMuonId;
    String cuonSachBarcode;
}
