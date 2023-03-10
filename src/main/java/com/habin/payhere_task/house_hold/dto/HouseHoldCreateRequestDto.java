package com.habin.payhere_task.house_hold.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
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
public class HouseHoldCreateRequestDto {

    @NotBlank(message = "카테고리를 입력해주세요.")
    @Size(max = 30, message = "카테고리는 30자 이하로 입력해주세요.")
    private String category;

    @NotBlank(message = "결제수단을 입력해주세요.")
    @Size(max = 30, message = "결제수단은 30자 이하로 입력해주세요.")
    private String payment;

    @NotNull(message = "금액을 입력해주세요.")
    @Size(max = 10, message = "금액은 10자 이하로 입력해주세요.")
    private String amount;

    @Size(max = 500, message = "메모는 500자 이하로 입력해주세요.")
    private String memo;

    @JsonIgnore
    private String userEmail;

}
