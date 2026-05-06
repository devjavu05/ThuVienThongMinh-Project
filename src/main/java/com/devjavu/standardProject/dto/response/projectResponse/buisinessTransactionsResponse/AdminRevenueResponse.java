package com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminRevenueResponse {
    double totalRevenue;
    double ebookRevenue;
    double fineRevenue;
    long ebookPurchaseCount;
    long paidFineCount;
    long pendingFineCount;
    double pendingFineAmount;
}
