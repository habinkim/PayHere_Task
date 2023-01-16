package com.habin.payhere_task.house_hold.dto;

import com.habin.payhere_task.house_hold.validation.annotation.AmountRange;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AmountRange
public class HouseHoldListRequestDto {

    @Builder.Default
    @NotNull(message = "페이지 번호를 입력하지 않으셨습니다.")
    @Min(value = 1, message = "페이지 번호는 1 이상의 정수를 입력해주세요.")
    private Integer pageNo = 1;

    @Builder.Default
    @NotNull(message = "페이지당 크기를 입력하지 않으셨습니다.")
    @Min(value = 10, message = "페이지당 크기는 10 이상의 정수를 입력해주세요.")
    private Integer pageSize = 10;

    @NotBlank(message = "사용자 이메일을 입력해주세요.")
    @Size(max = 30, message = "사용자 이메일은 30자 이하로 입력해주세요.")
    private String userEmail;

    @Size(max = 30, message = "카테고리는 30자 이하로 입력해주세요.")
    private String category;

    @Size(max = 30, message = "결제수단은 30자 이하로 입력해주세요.")
    private String payment;

    @Min(value = 0, message = "최소 금액은 0 이상의 정수를 입력해주세요.")
    private Integer minAmount;

    @Max(value = 0, message = "최대 금액은 0 이상의 정수를 입력해주세요.")
    private Integer maxAmount;

    @NotNull(message = "삭제 여부를 입력해주세요.")
    private Boolean isDeleted;

}
