package com.habin.payhere_task.house_hold.repository;

import com.habin.payhere_task.common.jpa.PredicateBuilder;
import com.habin.payhere_task.house_hold.dto.HouseHoldDetailResponseDto;
import com.habin.payhere_task.house_hold.dto.HouseHoldListRequestDto;
import com.habin.payhere_task.house_hold.dto.HouseHoldListResponseDto;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.habin.payhere_task.house_hold.entity.QHouseHold.houseHold;
import static com.habin.payhere_task.user.entity.QUser.user;

@RequiredArgsConstructor
public class QHouseHoldRepositoryImpl implements QHouseHoldRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<HouseHoldListResponseDto> list(HouseHoldListRequestDto dto) {
        PageRequest pageRequest = PageRequest.of(dto.getPageNo() - 1, dto.getPageSize());

        Predicate predicate = PredicateBuilder.builder()
                .containsString(houseHold.category, dto.getCategory())
                .containsString(houseHold.payment, dto.getPayment())
                .betweenNumberDynamic(houseHold.amount,
                        dto.getMinAmount(), dto.getMaxAmount())
                .build();

        List<HouseHoldListResponseDto> fetch = queryFactory
                .select(Projections.fields(HouseHoldListResponseDto.class,
                        houseHold.houseHoldId,
                        houseHold.category,
                        houseHold.amount
                ))
                .from(houseHold)
                .join(houseHold.user, user)
                .where(predicate,
                        user.email.eq(dto.getUserEmail()),
                        houseHold.isDeleted.eq(dto.getIsDeleted())
                )
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(houseHold.houseHoldId.count())
                .from(houseHold)
                .join(houseHold.user, user)
                .where(predicate,
                        user.email.eq(dto.getUserEmail()),
                        houseHold.isDeleted.eq(dto.getIsDeleted())
                );

        return PageableExecutionUtils.getPage(fetch, pageRequest, countQuery::fetchOne);
    }

    @Override
    public HouseHoldDetailResponseDto detail(Long houseHoldId) {
        return queryFactory
                .select(Projections.fields(HouseHoldDetailResponseDto.class,
                        houseHold.houseHoldId,
                        houseHold.category,
                        houseHold.payment,
                        houseHold.amount,
                        houseHold.memo,
                        user.email.as("userEmail"),
                        user.nickname,
                        houseHold.insDtm.as("insDtm")
                ))
                .from(houseHold)
                .join(houseHold.user, user)
                .where(houseHold.houseHoldId.eq(houseHoldId))
                .fetchFirst();
    }

    @Override
    public long updateIsDeleted(List<Long> id, Boolean isDeleted) {
        return queryFactory.update(houseHold)
                .set(houseHold.isDeleted, isDeleted)
                .where(houseHold.houseHoldId.in(id))
                .execute();
    }
}
