package com.devjavu.standardProject.controller;

import com.devjavu.standardProject.dto.request.projectRequest.userProfilesRequest.NhanVienCreationRequest;
import com.devjavu.standardProject.dto.response.projectResponse.userProfileResponse.NhanVienResponse;
import com.devjavu.standardProject.dto.response.standardResponse.ApiResponse;
import com.devjavu.standardProject.service.projectService.NhanVienService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
@RequestMapping("/nhanvien")
@Tag(name = "Nhan Vien")
@SecurityRequirement(name = "bearerAuth")
public class NhanVienController {
    NhanVienService nhanVienService;
    @PreAuthorize("hasAuthority('CREATE_NHAN_VIEN')")
    @PostMapping
    public ApiResponse<NhanVienResponse> createNhanVien(@RequestBody NhanVienCreationRequest request){
        return ApiResponse.<NhanVienResponse>builder()
                .result(nhanVienService.createNhanVien(request))
                .build();
    }
}
