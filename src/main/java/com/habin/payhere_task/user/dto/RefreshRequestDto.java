package com.habin.payhere_task.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshRequestDto {

    @NotBlank(message = "Refresh Token을 입력하지 않으셨습니다.")
    private String refreshToken;

}
