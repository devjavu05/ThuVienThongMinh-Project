package com.devjavu.standardProject.controller;

import com.devjavu.standardProject.dto.request.standardRequest.PermissionRequest;
import com.devjavu.standardProject.dto.response.standardResponse.ApiResponse;
import com.devjavu.standardProject.dto.response.standardResponse.PermissionResponse;
import com.devjavu.standardProject.service.standardService.PermissionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
@RequestMapping("/permissions")
@Tag(name = "Permissions")
@SecurityRequirement(name = "bearerAuth")
public class PermissionController {
    PermissionService permissionService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.createPermission(request))
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public  ApiResponse<List<PermissionResponse>> getPermissions(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getPermissions())
                .build();
    }
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{name}")
    public ApiResponse<?> deletePermission(@PathVariable String name){
        permissionService.deletePermission(name);
        return ApiResponse.builder()
                .build();
    }
}
