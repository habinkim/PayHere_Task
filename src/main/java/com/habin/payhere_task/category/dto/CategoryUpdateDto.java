package com.habin.payhere_task.category.dto;

import jakarta.validation.constraints.Max;
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
public class CategoryUpdateDto {

    @NotNull(message = "카테고리 ID를 입력하지 않으셨습니다.")
    private Integer id;

    @Size(max = 20,  message = "카테고리명은 20자 제한입니다.")
    private String name;

    @Max(value = 9999999999L, message = "부모 카테고리 ID는 10자리 이하의 숫자만 입력 가능합니다.")
    private Integer parentCategoryId;

}
