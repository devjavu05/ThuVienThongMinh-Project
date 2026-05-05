package com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse;

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
public class TraCuuTaiLieuResponse {
    String id;
    String title;
    String author;
    String category;
    Integer publishYear;
    String coverImageUrl;
    Double averageRating;
    int availableCount;
    String tinhTrang;
    boolean hasEBook;
    String eBookLink;
}
