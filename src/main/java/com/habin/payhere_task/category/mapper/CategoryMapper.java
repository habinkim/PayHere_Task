package com.habin.payhere_task.category.mapper;

import com.habin.payhere_task.category.dto.CategoryCreateDto;
import com.habin.payhere_task.category.dto.CategoryUpdateDto;
import com.habin.payhere_task.category.entity.Category;
import com.habin.payhere_task.common.mapper.EntityMapper;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = {EntityMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parent", source = "parentCategoryId")
    @Mapping(target = "houseHolds", ignore = true)
    @Mapping(target = "childs", ignore = true)
    Category createDtoToEntity(CategoryCreateDto categoryCreateDto);


    @Mapping(target = "parent", source = "parentCategoryId")
    @Mapping(target = "houseHolds", ignore = true)
    @Mapping(target = "childs", ignore = true)
    Category.CategoryBuilder<?, ?> updateDtoToEntity(CategoryUpdateDto categoryUpdateDto,
                                                     @MappingTarget Category.CategoryBuilder<?, ?> categoryBuilder);

}
