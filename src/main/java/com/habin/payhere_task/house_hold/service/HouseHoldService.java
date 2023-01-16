package com.habin.payhere_task.house_hold.service;

import com.habin.payhere_task.common.dto.ApiResponse;
import com.habin.payhere_task.house_hold.dto.HouseHoldCreateRequestDto;
import com.habin.payhere_task.house_hold.dto.HouseHoldDetailResponseDto;
import com.habin.payhere_task.house_hold.dto.HouseHoldListRequestDto;
import com.habin.payhere_task.house_hold.dto.HouseHoldListResponseDto;
import com.habin.payhere_task.house_hold.repository.HouseHoldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseHoldService {

    private final HouseHoldRepository houseHoldRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<Page<HouseHoldListResponseDto>>> list(HouseHoldListRequestDto dto) {
        Page<HouseHoldListResponseDto> list = houseHoldRepository.list(dto);
        return ApiResponse.success(list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<HouseHoldDetailResponseDto>> detail(Long houseHoldId) {
        HouseHoldDetailResponseDto detail = houseHoldRepository.detail(houseHoldId);
        return ApiResponse.success(detail);
    }

    @Transactional
    public ResponseEntity<ApiResponse<?>> create(HouseHoldCreateRequestDto dto) {
        return null;
    }
}
