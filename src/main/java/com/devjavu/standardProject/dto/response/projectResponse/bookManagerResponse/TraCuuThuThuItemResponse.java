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
public class TraCuuThuThuItemResponse {
    String id;
    String title;
    String author;
    String category;
    String description;
    String longIntroduction;
    Integer floorNumber;
    String shelfCode;
    String defaultLocation;
    String coverImageUrl;
    Integer publishYear;
    List<String> shelfLocations;
    List<String> availableBarcodes;
    int totalCopies;
    int availableCopies;
    int borrowedCopies;
    int lostCopies;
    int damagedCopies;
}
