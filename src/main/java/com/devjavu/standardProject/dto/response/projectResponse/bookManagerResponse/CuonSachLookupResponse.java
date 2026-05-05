package com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CuonSachLookupResponse {
    String bookId;
    String barcode;
    String title;
    String author;
    String category;
    String defaultLocation;
    String location;
    String status;
    String physicalCondition;
    Boolean available;
    String borrowerFullName;
    String borrowerEmail;
    LocalDate borrowDate;
    LocalDate dueDate;
}
