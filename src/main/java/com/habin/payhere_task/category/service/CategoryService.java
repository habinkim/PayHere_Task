package com.habin.payhere_task.category.service;

import com.habin.payhere_task.category.dto.*;
import com.habin.payhere_task.category.entity.Category;
import com.habin.payhere_task.category.mapper.CategoryMapper;
import com.habin.payhere_task.category.repository.CategoryRepository;
import com.habin.payhere_task.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<Page<CategoryListResponseDto>>> list(CategoryListRequestDto categoryListRequestDto) {
        Page<CategoryListResponseDto> list = categoryRepository.list(categoryListRequestDto);
        return ApiResponse.success(list);
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> create(CategoryCreateDto categoryCreateDto) {
        Category category = categoryMapper.createDtoToEntity(categoryCreateDto);
        categoryRepository.save(category);
        return ApiResponse.success();
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> update(CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryRepository.findById(categoryUpdateDto.getId())
                .orElseThrow(() -> new NoSuchElementException("카테고리가 존재하지 않습니다."));

        category = categoryMapper.updateDtoToEntity(categoryUpdateDto, category.toBuilder()).build();
        categoryRepository.save(category);

        return ApiResponse.success();
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> delete(CategoryIdList categoryIdList) {
        categoryRepository.deleteAllById(categoryIdList.getId());
        return ApiResponse.success();
    }
}
