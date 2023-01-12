package com.habin.payhere_task.category.repository;

import com.habin.payhere_task.category.dto.CategoryListRequestDto;
import com.habin.payhere_task.category.dto.CategoryListResponseDto;
import com.habin.payhere_task.category.entity.QCategory;
import com.habin.payhere_task.common.jpa.PredicateBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.habin.payhere_task.category.entity.QCategory.category;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.types.Projections.fields;
import static org.springframework.data.support.PageableExecutionUtils.getPage;

@RequiredArgsConstructor
public class QCategoryRepositoryImpl implements QCategoryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CategoryListResponseDto> list(CategoryListRequestDto categoryListRequestDto) {
        PageRequest pageRequest = categoryListRequestDto.getPageRequest();

        QCategory parent = new QCategory("parent");
        QCategory child = new QCategory("child");

        Predicate predicate = PredicateBuilder.builder()
                .containsString(category.name, categoryListRequestDto.getName())
                .eqString(parent.name, categoryListRequestDto.getParentCategoryNm())
                .build();

        List<CategoryListResponseDto> result = queryFactory.select()
                .from(category)
                .leftJoin(category.parent, parent)
                .leftJoin(category.childs, child)
                .where(predicate)
                .transform(groupBy(category.id)
                        .list(fields(CategoryListResponseDto.class,
                                        category.id.as("categoryId"),
                                        category.name.as("categoryNm"),
                                        parent.id.as("parentCategoryId"),
                                        parent.name.as("parentCategoryNm"),
                                        fields(CategoryListResponseDto.class,
                                                child.id.as("categoryId"),
                                                child.name.as("categoryNm")
                                        ).as("childs")
                                )
                        )
                );

        JPAQuery<Long> countQuery = queryFactory.select(category.id.count())
                .from(category)
                .leftJoin(category.parent, parent)
                .leftJoin(category.childs, child)
                .where(predicate);

        return getPage(result, pageRequest, countQuery::fetchOne);
    }
}
