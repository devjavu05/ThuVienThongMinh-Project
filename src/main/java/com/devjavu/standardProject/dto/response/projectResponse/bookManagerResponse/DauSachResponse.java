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
    int quantity;
}
