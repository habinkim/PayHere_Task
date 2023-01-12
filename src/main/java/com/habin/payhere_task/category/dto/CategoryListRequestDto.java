package com.habin.payhere_task.category.dto;

import com.habin.payhere_task.common.dto.PageRequestDto;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListRequestDto extends PageRequestDto {

    @Size(max = 40, message = "카테고리 이름은 40자 제한입니다.")
    private String name;

    @Size(max = 40, message = "카테고리 이름은 40자 제한입니다.")
    private String parentCategoryNm;

}
