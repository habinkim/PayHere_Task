package com.habin.payhere_task.house_hold.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HouseHoldUpdateRequestDto {

    @NotNull(message = "가계부 아이디를 입력해주세요.")
    private Long houseHoldId;

    @Size(max = 30, message = "카테고리는 30자 이하로 입력해주세요.")
    private String category;

    @Size(max = 30, message = "결제수단은 30자 이하로 입력해주세요.")
    private String payment;

    @Size(max = 10, message = "금액은 10자 이하로 입력해주세요.")
    private String amount;

    @Size(max = 500, message = "메모는 500자 이하로 입력해주세요.")
    private String memo;


}
