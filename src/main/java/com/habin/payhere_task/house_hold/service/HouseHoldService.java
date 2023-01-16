package com.habin.payhere_task.house_hold.service;

import com.habin.payhere_task.common.dto.ApiResponse;
import com.habin.payhere_task.common.security.JwtTokenProvider;
import com.habin.payhere_task.house_hold.dto.*;
import com.habin.payhere_task.house_hold.entity.HouseHold;
import com.habin.payhere_task.house_hold.mapper.HouseHoldMapper;
import com.habin.payhere_task.house_hold.repository.HouseHoldRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseHoldService {

    private final HouseHoldRepository houseHoldRepository;
    private final HouseHoldMapper houseHoldMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<Page<HouseHoldListResponseDto>>> list(HouseHoldListRequestDto dto) {
        dto = dto.toBuilder().userEmail(getUserEmailFromToken()).build();
        Page<HouseHoldListResponseDto> list = houseHoldRepository.list(dto);
        return ApiResponse.success(list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<HouseHoldDetailResponseDto>> detail(Long houseHoldId) {
        HouseHoldDetailResponseDto detail = houseHoldRepository.detail(houseHoldId);
        return ApiResponse.success(detail);
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> create(HouseHoldCreateRequestDto dto) {
        dto = dto.toBuilder().userEmail(getUserEmailFromToken()).build();
        HouseHold houseHold = houseHoldMapper.createDtoToEntity(dto);
        houseHoldRepository.save(houseHold);
        return ApiResponse.success();
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> update(HouseHoldUpdateRequestDto dto) {
        HouseHold houseHold = houseHoldRepository.findById(dto.getHouseHoldId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가계부입니다."));

        houseHold = houseHoldMapper.updateDtoToEntity(dto, houseHold.toBuilder()).build();
        houseHoldRepository.save(houseHold);

        return ApiResponse.success();
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> delete(HouseHoldDeleteRequestDto dto) {
        houseHoldRepository.updateIsDeleted(dto.getId(), true);
        return ApiResponse.success();
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> cancelDelete(HouseHoldDeleteRequestDto dto) {
        houseHoldRepository.updateIsDeleted(dto.getId(), false);
        return ApiResponse.success();
    }

    private String getUserEmailFromToken() {
        HttpServletRequest request = ((ServletRequestAttributes) currentRequestAttributes()).getRequest();
        String accessToken = jwtTokenProvider.resolveToken(request);
        return jwtTokenProvider.getEmailFromToken(accessToken);
    }
}
