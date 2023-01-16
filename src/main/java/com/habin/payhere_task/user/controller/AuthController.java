package com.habin.payhere_task.user.controller;

import com.habin.payhere_task.common.dto.ApiResponse;
import com.habin.payhere_task.user.dto.LoginRequestDto;
import com.habin.payhere_task.user.dto.SignUpRequestDto;
import com.habin.payhere_task.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "인증 관리", description = "인증 관리 API")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Tag(name = "인증 관리", description = "인증 관리 API")
    @Operation(summary = "회원가입", description = "회원가입 API")
    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse<Object>> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        return authService.signUp(signUpRequestDto);
    }

    @Tag(name = "인증 관리", description = "인증 관리 API")
    @Operation(summary = "로그인", description = "로그인 API")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @Tag(name = "인증 관리", description = "인증 관리 API")
    @Operation(summary = "로그아웃", description = "로그아웃 API")
    @SecurityRequirement(name = "TOKEN")
    @GetMapping("/logoutUser")
    public ResponseEntity<ApiResponse<String>> logout() {
        return authService.logout();
    }

    @Tag(name = "인증 관리", description = "인증 관리 API")
    @Operation(summary = "Access Token 재발급", description = "Access Token 재발급 API")
    @SecurityRequirement(name = "TOKEN")
    @GetMapping("/refresh")
    public ResponseEntity<ApiResponse<?>> refresh() {
        return authService.refresh();
    }

}
