package com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PhieuDatTruocResponse {
    String id;
    LocalDate reservationDate;
    String status;
    String fullName;
    String email;
    String dauSachId;
    String title;
    Integer queuePosition;
}
