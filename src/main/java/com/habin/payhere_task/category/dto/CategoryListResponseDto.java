package com.habin.payhere_task.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListResponseDto {

    private Integer categoryId;
    private String categoryNm;
    private Integer parentCategoryId;
    private String parentCategoryNm;
    private List<CategoryListResponseDto> childs;

}
