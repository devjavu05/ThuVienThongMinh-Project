package com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class LichSuMuonTraItemResponse {
    Long id;
    String phieuMuonId;
    String cuonSachBarcode;
    String bookTitle;
    LocalDate borrowDate;
    LocalDate dueDate;
    LocalDate returnDate;
    String status;
    int renewalCount;
    boolean overdue;
    boolean canRenew;
    String renewBlockedReason;
}
