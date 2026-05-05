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
public class DanhGiaResponse {
    String id;
    int rating;
    String comment;
    LocalDateTime updatedAt;
    String fullName;
    String dauSachId;
}
