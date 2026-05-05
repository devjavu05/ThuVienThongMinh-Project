package com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTietTaiLieuResponse {
    String id;
    String title;
    String author;
    String category;
    String description;
    String longIntroduction;
    Integer floorNumber;
    String shelfCode;
    String defaultLocation;
    Integer publishYear;
    String coverImageUrl;
    Double averageRating;
    int totalQuantity;
    int availableCount;
    String tinhTrang;
    List<String> viTriKe;
    boolean hasEBook;
    String eBookLink;
    String eBookFormat;
    Double eBookFileSize;
    Double eBookPrice;
    Boolean eBookPremiumOnly;
    Boolean eBookDownloadable;
    Boolean eBookUnderMaintenance;
    Boolean eBookOwned;
    String ownedAccessLink;
    Boolean canReview;
}
