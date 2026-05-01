package com.devjavu.standardProject.controller;

import com.devjavu.standardProject.dto.request.standardRequest.UserCreationRequest;
import com.devjavu.standardProject.dto.request.standardRequest.UserUpdateRequest;
import com.devjavu.standardProject.dto.response.standardResponse.ApiResponse;
import com.devjavu.standardProject.dto.response.standardResponse.UserResponse;
import com.devjavu.standardProject.service.standardService.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/users")
@Tag(name = "Users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    UserService userService;
    @Operation(summary = "Tao user", security = {})
    @PostMapping
    public ApiResponse<UserResponse> creatUser(@RequestBody UserCreationRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResponse<List<UserResponse>> getUser(){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUser())
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{username}")
    public ApiResponse<UserResponse> updateUser(@RequestBody UserUpdateRequest request,@PathVariable String username){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(username,request))
                .build();
    }
}
