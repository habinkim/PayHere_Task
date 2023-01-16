package com.habin.payhere_task.user.service;

import com.habin.payhere_task.common.dto.ApiResponse;
import com.habin.payhere_task.common.security.JwtTokenProvider;
import com.habin.payhere_task.common.security.token.AccessToken;
import com.habin.payhere_task.common.security.token.RefreshToken;
import com.habin.payhere_task.common.yml.JwtProperty;
import com.habin.payhere_task.user.dto.LoginRequestDto;
import com.habin.payhere_task.user.dto.RefreshRequestDto;
import com.habin.payhere_task.user.dto.SignUpRequestDto;
import com.habin.payhere_task.user.entity.User;
import com.habin.payhere_task.user.mapper.UserMapper;
import com.habin.payhere_task.user.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, AccessToken> redisTemplateAccess;
    private final RedisTemplate<String, RefreshToken> redisTemplateRefresh;
    private final RedisTemplate<String, Object> redisTemplateObject;
    private final JwtProperty jwtProperty;

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
    public ResponseEntity<ApiResponse<String>> logout() {
        HttpServletRequest request = ((ServletRequestAttributes) currentRequestAttributes()).getRequest();
        ValueOperations<String, AccessToken> accessVop = redisTemplateAccess.opsForValue();
        ValueOperations<String, RefreshToken> refreshVop = redisTemplateRefresh.opsForValue();
        ValueOperations<String, Object> objectVop = redisTemplateObject.opsForValue();

        String userEmail = null;
        String accessToken = jwtTokenProvider.resolveToken(request);

        try {
            userEmail = jwtTokenProvider.getEmailFromToken(accessToken);
        } catch (IllegalArgumentException ignored) {
        } catch (ExpiredJwtException e) {
            userEmail = e.getClaims().getSubject();
            log.info("user_id from expired access token : " + userEmail);
        }

        if (accessVop.get("access_" + userEmail) != null) {
            redisTemplateAccess.delete("access_" + userEmail);
        }

        try {
            RefreshToken refreshTokenObject = refreshVop.get("refresh_" + userEmail);
            if (refreshTokenObject != null) {
                redisTemplateAccess.delete("refresh_" + userEmail);
                objectVop.set(accessToken, true);
                redisTemplateObject.expire(accessToken, jwtProperty.getAccessTokenValidityDuration());
            }
        } catch (IllegalArgumentException e) {
            log.warn("user does not exist");
        }

        log.info(" logout ing : " + accessToken);

        return ApiResponse.success("로그아웃 되었습니다.");
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> refresh(RefreshRequestDto dto) {
        if (!jwtTokenProvider.isRefreshTokenExpired(dto.getRefreshToken())) { // refresh token 만료되지 않았을때 -> 여기서 예외가 터짐
            String userId = jwtTokenProvider.getEmailFromToken(dto.getRefreshToken()); // access_token에서 user_id 가져옴(유효성 검사)
            User user = userRepository.findById(userId).orElseThrow((() -> new NoSuchElementException("가입되지 않은 계정입니다.")));

            AccessToken newAccessToken = jwtTokenProvider.createAccessToken(user.getEmail());

            return ApiResponse.success(newAccessToken);
        } else { // refresh 토큰 expire
            return ApiResponse.badRequest("refreshToken이 만료되었습니다. 다시 로그인해주세요.");
        }
    }
}
