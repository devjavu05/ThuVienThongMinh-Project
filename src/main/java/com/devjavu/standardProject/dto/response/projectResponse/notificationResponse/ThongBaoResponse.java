package com.devjavu.standardProject.dto.response.projectResponse.notificationResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ThongBaoResponse {
    String id;
    String title;
    String content;
    String type;
    boolean read;
    LocalDateTime createdAt;
}
