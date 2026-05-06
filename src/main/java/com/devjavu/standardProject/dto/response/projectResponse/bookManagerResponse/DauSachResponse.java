package com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DauSachResponse {
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
    int quantity;
    boolean hasEBook;
    String eBookLink;
    String eBookFormat;
    Double eBookFileSize;
    Double eBookPrice;
    Boolean eBookPremiumOnly;
    Boolean eBookDownloadable;
    Boolean eBookUnderMaintenance;
}
