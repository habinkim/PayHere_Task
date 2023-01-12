package com.habin.payhere_task.category.dto;

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
public class CategoryIdList {

    @NotNull(message = "카테고리 ID 리스트를 입력하지 않으셨습니다.")
    @NotEmpty(message = "카테고리 ID 리스트를 입력하지 않으셨습니다.")
    private List<Integer> id;

}
