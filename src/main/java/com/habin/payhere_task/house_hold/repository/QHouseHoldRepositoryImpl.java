package com.habin.payhere_task.house_hold.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QHouseHoldRepositoryImpl implements QHouseHoldRepository {

    private final JPAQueryFactory queryFactory;


}
