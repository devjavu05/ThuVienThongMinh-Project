package com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TraCuuThuThuResponse {
    List<TraCuuThuThuItemResponse> items;
    int page;
    int size;
    long totalItems;
    int totalPages;
}
