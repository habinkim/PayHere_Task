package com.habin.payhere_task.user.service;

import com.habin.payhere_task.common.dto.ApiResponse;
import com.habin.payhere_task.common.security.JwtTokenProvider;
import com.habin.payhere_task.common.security.token.AccessToken;
import com.habin.payhere_task.common.security.token.RefreshToken;
import com.habin.payhere_task.user.dto.LoginRequestDto;
import com.habin.payhere_task.user.dto.SignUpRequestDto;
import com.habin.payhere_task.user.entity.User;
import com.habin.payhere_task.user.mapper.UserMapper;
import com.habin.payhere_task.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<ApiResponse<Object>> signUp(SignUpRequestDto signUpRequestDto) {
        Optional<User> byId = userRepository.findById(signUpRequestDto.getEmail());
        if (byId.isPresent()) {
            return ApiResponse.badRequest("이미 가입된 아이디입니다.");
        }

        User user = userMapper.toEntity(signUpRequestDto);
        userRepository.save(user);

        return ApiResponse.success();
    }

    @Transactional
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findById(loginRequestDto.getEmail())
                .orElseThrow(() -> new NoSuchElementException("가입되지 않은 계정입니다."));

        boolean pwIsMatch = passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());

        if (pwIsMatch) {
            AccessToken accessToken = jwtTokenProvider.createAccessToken(loginRequestDto.getEmail());
            RefreshToken refreshToken = jwtTokenProvider.createRefreshToken(loginRequestDto.getEmail());

            Map<String, Object> result = new HashMap<>();
            result.put("accessToken", accessToken);
            result.put("refreshToken", refreshToken);

            return ApiResponse.success(result);
        } else {
            return ApiResponse.badRequest("비밀번호가 일치하지 않습니다.");
        }
    }

    @Transactional
    public ResponseEntity<ApiResponse<?>> logout() {
        return null;
    }

    @Transactional
    public ResponseEntity<ApiResponse<?>> refresh() {
        return null;
    }
}
