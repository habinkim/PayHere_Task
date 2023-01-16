package com.habin.payhere_task.user.mapper;

import com.habin.payhere_task.common.mapper.EntityMapper;
import com.habin.payhere_task.user.dto.SignUpRequestDto;
import com.habin.payhere_task.user.entity.User;
import com.habin.payhere_task.user.mapper.decorator.UserDecorator;
import org.mapstruct.*;

@Mapper(
		componentModel = "spring",
		uses = {EntityMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@DecoratedWith(UserDecorator.class)
public interface UserMapper {

    User toEntity(SignUpRequestDto signUpRequestDto);

}
