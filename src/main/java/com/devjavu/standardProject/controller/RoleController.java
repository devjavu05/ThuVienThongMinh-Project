package com.devjavu.standardProject.controller;

import com.devjavu.standardProject.dto.request.standardRequest.RoleRequest;
import com.devjavu.standardProject.dto.response.standardResponse.ApiResponse;
import com.devjavu.standardProject.dto.response.standardResponse.RoleResponse;
import com.devjavu.standardProject.service.standardService.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/roles")
@Tag(name = "Roles")
@SecurityRequirement(name = "bearerAuth")
public class RoleController {
    RoleService roleService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(request))
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResponse<List<RoleResponse>> getRole(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getRole())
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{role}")
    public ApiResponse<String> deleteRole(@PathVariable String role){
        roleService.deleteRole(role);
        return ApiResponse.<String>builder()
                .result("Has been deleted !")
                .build();
    }
}
