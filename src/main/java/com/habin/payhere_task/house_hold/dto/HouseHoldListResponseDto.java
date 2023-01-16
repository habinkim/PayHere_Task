package com.habin.payhere_task.house_hold.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HouseHoldListResponseDto {
    private Long houseHoldId;
    private String category;
    private Integer amount;
}
