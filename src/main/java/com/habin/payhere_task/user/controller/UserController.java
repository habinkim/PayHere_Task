package com.habin.payhere_task.user.controller;

import com.habin.payhere_task.common.dto.ApiResponse;
import com.habin.payhere_task.user.dto.LoginRequestDto;
import com.habin.payhere_task.user.dto.SignUpRequestDto;
import com.habin.payhere_task.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse<Object>> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        return userService.signUp(signUpRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    @GetMapping("/logoutUser")
    public ResponseEntity<ApiResponse<?>> logout() {
        return userService.logout();
    }

    @GetMapping("/refresh")
    public ResponseEntity<ApiResponse<?>> refresh() {
        return userService.refresh();
    }

}
