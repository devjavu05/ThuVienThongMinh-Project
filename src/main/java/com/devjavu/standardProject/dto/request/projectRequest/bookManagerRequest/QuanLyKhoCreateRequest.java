package com.devjavu.standardProject.dto.request.projectRequest.bookManagerRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuanLyKhoCreateRequest {
    String title;
    String author;
    String category;
    String description;
    String longIntroduction;
    Integer floorNumber;
    Integer publishYear;
    String coverImageUrl;
    Integer copyCount;
    String physicalCondition;
    String accessLink;
}
