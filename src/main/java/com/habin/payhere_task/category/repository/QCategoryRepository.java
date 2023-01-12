package com.habin.payhere_task.category.repository;

import com.habin.payhere_task.category.dto.CategoryListRequestDto;
import com.habin.payhere_task.category.dto.CategoryListResponseDto;
import org.springframework.data.domain.Page;

public interface QCategoryRepository {
    Page<CategoryListResponseDto> list(CategoryListRequestDto categoryListRequestDto);
}
