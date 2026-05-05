package com.devjavu.standardProject.controller;


import com.devjavu.standardProject.dto.request.projectRequest.userProfilesRequest.BalanceUpdateRequest;
import com.devjavu.standardProject.dto.request.projectRequest.userProfilesRequest.DocGiaCreationRequest;
import com.devjavu.standardProject.dto.response.projectResponse.userProfileResponse.DocGiaResponse;
import com.devjavu.standardProject.dto.response.standardResponse.ApiResponse;
import com.devjavu.standardProject.service.projectService.DocGiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
@RequestMapping("/docgia")
@Tag(name = "Doc Gia")
public class DocGiaController {
    DocGiaService docGiaService;

    @Operation(summary = "Dang ky doc gia", security = {})
    @PostMapping
    public ApiResponse<DocGiaResponse> createDocGia(@RequestBody DocGiaCreationRequest request){
        return ApiResponse.<DocGiaResponse>builder()
                .result(docGiaService.createDocgia(request))
                .build();
    }

    @PreAuthorize("hasRole('DOC_GIA')")
    @GetMapping("/me")
    public ApiResponse<DocGiaResponse> getMyProfile() {
        return ApiResponse.<DocGiaResponse>builder()
                .result(docGiaService.getMyProfile())
                .build();
    }

    @PreAuthorize("hasRole('DOC_GIA')")
    @PatchMapping("/me/balance")
    public ApiResponse<DocGiaResponse> updateMyBalance(@RequestBody BalanceUpdateRequest request) {
        return ApiResponse.<DocGiaResponse>builder()
                .result(docGiaService.updateMyBalance(request))
                .build();
    }
}
