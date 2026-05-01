package com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PhieuPhatResponse {
    String id;
    double amount;
    String reason;
    boolean paid;
    String phieuMuonId;
    String cuonSachBarcode;
    String bookTitle;
}
