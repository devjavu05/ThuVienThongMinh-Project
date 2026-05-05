package com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhieuMuaResponse {
    String id;
    LocalDateTime purchaseTime;
    double amount;
    String title;
    String accessLink;
    String format;
}
