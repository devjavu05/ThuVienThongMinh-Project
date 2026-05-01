package com.devjavu.standardProject.dto.response.projectResponse.userProfileResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DocGiaResponse {
    String username;
    String email;
    String fullName;
}
