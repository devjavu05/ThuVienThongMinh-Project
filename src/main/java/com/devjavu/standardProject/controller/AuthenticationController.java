package com.devjavu.standardProject.controller;

import com.devjavu.standardProject.dto.request.standardRequest.AuthenticationRequest;
import com.devjavu.standardProject.dto.request.standardRequest.ForgotPasswordRequest;
import com.devjavu.standardProject.dto.response.standardResponse.ApiResponse;
import com.devjavu.standardProject.dto.response.standardResponse.AuthenticationResponse;
import com.devjavu.standardProject.dto.response.standardResponse.LogoutResponse;
import com.devjavu.standardProject.service.standardService.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
@RequestMapping("/auths")
@Tag(name = "Authentication")
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping
    public ApiResponse<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest request){
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.loginUser(request))
                .build();
    }

    @PostMapping("/forgot-password")
    public ApiResponse<String> forgotPassword(@RequestBody ForgotPasswordRequest request){
        return ApiResponse.<String>builder()
                .result(authenticationService.forgotPassword(request))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<LogoutResponse> logout(@RequestHeader("Authorization") String authorizationHeader) {
        return ApiResponse.<LogoutResponse>builder()
                .result(authenticationService.logout(authorizationHeader))
                .build();
    }
}
