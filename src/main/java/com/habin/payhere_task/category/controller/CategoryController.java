package com.habin.payhere_task.category.controller;

import com.habin.payhere_task.category.dto.*;
import com.habin.payhere_task.category.service.CategoryService;
import com.habin.payhere_task.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/list")
    public ResponseEntity<ApiResponse<Page<CategoryListResponseDto>>> list(@Valid @RequestBody CategoryListRequestDto categoryListRequestDto) {
        return categoryService.list(categoryListRequestDto);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> create(@Valid @RequestBody CategoryCreateDto categoryCreateDto) {
        return categoryService.create(categoryCreateDto);
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<Object>> update(@Valid @RequestBody CategoryUpdateDto categoryUpdateDto) {
        return categoryService.update(categoryUpdateDto);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Object>> delete(@Valid @RequestBody CategoryIdList categoryIdList) {
        return categoryService.delete(categoryIdList);
    }

}
