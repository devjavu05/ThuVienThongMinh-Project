package com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CuonSachResponse {
    String barcode;
    int stt;
    String location;
    String status;

    String dauSach;
}
