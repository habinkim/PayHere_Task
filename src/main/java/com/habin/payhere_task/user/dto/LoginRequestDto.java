package com.habin.payhere_task.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "이메일을 입력하지 않으셨습니다.")
    @Size(max = 30, message = "이메일은 30자 이내여야 합니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력하지 않으셨습니다.")
    @Pattern(message = "비밀번호는 영문, 숫자를 포함한 8 ~ 20자리여야 합니다.", regexp = "^(?=.*[a-z])(?=.*[0-9]).{8,20}$")
    private String password;

}
