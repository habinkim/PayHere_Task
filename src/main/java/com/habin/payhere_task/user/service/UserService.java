package com.habin.payhere_task.user.service;

import com.habin.payhere_task.common.dto.ApiResponse;
import com.habin.payhere_task.user.dto.LoginRequestDto;
import com.habin.payhere_task.user.dto.SignUpRequestDto;
import com.habin.payhere_task.user.entity.User;
import com.habin.payhere_task.user.mapper.UserMapper;
import com.habin.payhere_task.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
    public ResponseEntity<ApiResponse<?>> login(LoginRequestDto loginRequestDto) {
        return null;
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
