package com.habin.payhere_task.house_hold.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HouseHoldDeleteRequestDto {

    @NotNull(message = "삭제할 가계부 아이디를 입력해주세요.")
    @NotEmpty(message = "삭제할 가계부 아이디를 입력해주세요.")
    private List<Long> id;

}
