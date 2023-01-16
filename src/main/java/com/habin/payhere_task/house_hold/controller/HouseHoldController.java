package com.habin.payhere_task.house_hold.controller;

import com.habin.payhere_task.common.dto.ApiResponse;
import com.habin.payhere_task.house_hold.dto.HouseHoldCreateRequestDto;
import com.habin.payhere_task.house_hold.dto.HouseHoldDetailResponseDto;
import com.habin.payhere_task.house_hold.dto.HouseHoldListRequestDto;
import com.habin.payhere_task.house_hold.dto.HouseHoldListResponseDto;
import com.habin.payhere_task.house_hold.service.HouseHoldService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/houseHold")
public class HouseHoldController {

    private final HouseHoldService houseHoldService;

    @PostMapping("/list")
    public ResponseEntity<ApiResponse<Page<HouseHoldListResponseDto>>> list(@Valid @RequestBody HouseHoldListRequestDto dto) {
        return houseHoldService.list(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HouseHoldDetailResponseDto>> detail(@PathVariable(name = "id") Long houseHoldId) {
        return houseHoldService.detail(houseHoldId);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody HouseHoldCreateRequestDto dto) {
        return houseHoldService.create(dto);
    }

}
