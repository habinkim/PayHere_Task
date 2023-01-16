package com.habin.payhere_task.house_hold.repository;

import com.habin.payhere_task.house_hold.dto.HouseHoldDetailResponseDto;
import com.habin.payhere_task.house_hold.dto.HouseHoldListRequestDto;
import com.habin.payhere_task.house_hold.dto.HouseHoldListResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QHouseHoldRepository {
    Page<HouseHoldListResponseDto> list(HouseHoldListRequestDto dto);

    HouseHoldDetailResponseDto detail(Long houseHoldId);

    long updateIsDeleted(List<Long> id, Boolean isDeleted);
}
