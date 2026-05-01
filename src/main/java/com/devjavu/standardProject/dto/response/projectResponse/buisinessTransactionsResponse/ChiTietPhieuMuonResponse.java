package com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ChiTietPhieuMuonResponse {
    Long id;
    LocalDate returnDate;
    String status;
    String phieuMuonId;
    String cuonSachBarcode;
    String bookTitle;
}
