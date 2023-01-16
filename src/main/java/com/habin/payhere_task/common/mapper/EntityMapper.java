package com.habin.payhere_task.common.mapper;

import com.habin.payhere_task.user.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
		uses = {ReferenceMapper.class},
		builder = @Builder(disableBuilder = true),
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EntityMapper {
	User stringToUser(String email);
}
