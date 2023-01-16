package com.habin.payhere_task.house_hold.controller;

import com.habin.payhere_task.common.dto.ApiResponse;
import com.habin.payhere_task.house_hold.dto.*;
import com.habin.payhere_task.house_hold.service.HouseHoldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "가계부 관리", description = "가계부 관리 API")
@SecurityRequirement(name = "TOKEN")
@RestController
@RequiredArgsConstructor
@RequestMapping("/houseHold")
public class HouseHoldController {

    private final HouseHoldService houseHoldService;

    @Tag(name = "가계부 관리", description = "가계부 관리 API")
    @Operation(summary = "가계부 내역 리스트 조회", description = "가계부 내역 리스트 조회 API")
    @PostMapping("/list")
    public ResponseEntity<ApiResponse<Page<HouseHoldListResponseDto>>> list(@Valid @RequestBody HouseHoldListRequestDto dto) {
        return houseHoldService.list(dto);
    }

    @Tag(name = "가계부 관리", description = "가계부 관리 API")
    @Operation(summary = "가계부 내역 상세 조회", description = "가계부 내역 상세 조회 API")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HouseHoldDetailResponseDto>> detail(@PathVariable(name = "id") Long houseHoldId) {
        return houseHoldService.detail(houseHoldId);
    }

    @Tag(name = "가계부 관리", description = "가계부 관리 API")
    @Operation(summary = "가계부 내역 생성", description = "가계부 내역 생성 API")
    @PutMapping
    public ResponseEntity<ApiResponse<Object>> create(@Valid @RequestBody HouseHoldCreateRequestDto dto) {
        return houseHoldService.create(dto);
    }

    @Tag(name = "가계부 관리", description = "가계부 관리 API")
    @Operation(summary = "가계부 내역 수정", description = "가계부 내역 수정 API")
    @PatchMapping
    public ResponseEntity<ApiResponse<Object>> update(@Valid @RequestBody HouseHoldUpdateRequestDto dto) {
        return houseHoldService.update(dto);
    }

    @Tag(name = "가계부 관리", description = "가계부 관리 API")
    @Operation(summary = "가계부 내역 삭제", description = "가계부 내역 삭제 API")
    @DeleteMapping
    public ResponseEntity<ApiResponse<Object>> delete(@Valid @RequestBody HouseHoldDeleteRequestDto dto) {
        return houseHoldService.delete(dto);
    }

    @Tag(name = "가계부 관리", description = "가계부 관리 API")
    @Operation(summary = "가계부 내역 삭제 취소", description = "가계부 내역 삭제 취소 API")
    @PatchMapping("/delete/cancel")
    public ResponseEntity<ApiResponse<Object>> cancelDelete(@Valid @RequestBody HouseHoldDeleteRequestDto dto) {
        return houseHoldService.cancelDelete(dto);
    }

}
