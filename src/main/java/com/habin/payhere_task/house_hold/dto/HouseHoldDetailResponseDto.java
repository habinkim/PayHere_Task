package com.habin.payhere_task.house_hold.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HouseHoldDetailResponseDto {
    private Long houseHoldId;
    private String category;
    private String payment;
    private Integer amount;
    private String memo;
    private String userEmail;
    private String nickname;
    private LocalDateTime insDtm;
}
