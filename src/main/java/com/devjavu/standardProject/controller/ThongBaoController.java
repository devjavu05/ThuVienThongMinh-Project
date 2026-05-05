package com.devjavu.standardProject.controller;

import com.devjavu.standardProject.dto.response.projectResponse.notificationResponse.ThongBaoResponse;
import com.devjavu.standardProject.dto.response.standardResponse.ApiResponse;
import com.devjavu.standardProject.service.projectService.ThongBaoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/notifications")
public class ThongBaoController {
    ThongBaoService thongBaoService;

    @PreAuthorize("hasRole('DOC_GIA')")
    @GetMapping
    public ApiResponse<List<ThongBaoResponse>> getMyNotifications() {
        return ApiResponse.<List<ThongBaoResponse>>builder()
                .result(thongBaoService.getMyNotifications())
                .build();
    }

    @PreAuthorize("hasRole('DOC_GIA')")
    @GetMapping("/unread-count")
    public ApiResponse<Long> getUnreadCount() {
        return ApiResponse.<Long>builder()
                .result(thongBaoService.getUnreadCount())
                .build();
    }

    @PreAuthorize("hasRole('DOC_GIA')")
    @GetMapping("/{id}")
    public ApiResponse<ThongBaoResponse> getNotificationDetail(@PathVariable String id) {
        return ApiResponse.<ThongBaoResponse>builder()
                .result(thongBaoService.getNotificationDetail(id))
                .build();
    }

    @PreAuthorize("hasRole('DOC_GIA')")
    @PatchMapping("/mark-all-read")
    public ApiResponse<String> markAllAsRead() {
        thongBaoService.markAllAsRead();
        return ApiResponse.<String>builder()
                .result("marked")
                .build();
    }
}
