package com.habin.payhere_task.common.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.PageRequest;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDto {

    @Min(message = "페이지 번호는 1 이상으로 입력해주세요.", value = 1)
    @Max(message = "페이지 번호 최댓값을 초과했습니다.", value = Integer.MAX_VALUE)
    @NotNull(message = "페이지 번호는 필수 입력값입니다.")
    private Integer page;

    @Min(message = "페이지 크기는 10 이상으로 입력해주세요.", value = 10)
    @Max(message = "페이지 크기 최댓값을 초과했습니다.", value = Integer.MAX_VALUE)
    @NotNull(message = "페이지 크기는 필수 입력값입니다.")
    private Integer size;

    public PageRequest getPageRequest() {
        return PageRequest.of(page - 1, size);
    }

}
