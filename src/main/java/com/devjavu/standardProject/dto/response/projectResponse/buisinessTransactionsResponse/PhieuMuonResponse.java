package com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PhieuMuonResponse {
    String id;
    LocalDate borrowDate;
    LocalDate dueDate;
    boolean editable;
    int renewalCount;

    String fullName;
    String email;

    String creater;
    String createrNumber;
}
