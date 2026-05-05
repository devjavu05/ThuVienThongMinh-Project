package com.devjavu.standardProject.dto.response.standardResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AuthenticationResponse {
    String token;
    boolean authenticated;
    String username;
    String role;
    String redirectPath;
    String message;
}
