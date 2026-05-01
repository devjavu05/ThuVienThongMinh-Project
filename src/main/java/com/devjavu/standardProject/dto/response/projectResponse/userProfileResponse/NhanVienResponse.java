package com.devjavu.standardProject.dto.response.projectResponse.userProfileResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class NhanVienResponse {
    String username;
    String fullName;
    String phoneNumber;
}
