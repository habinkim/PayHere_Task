package com.habin.payhere_task.house_hold.mapper;

import com.habin.payhere_task.common.mapper.EntityMapper;
import com.habin.payhere_task.house_hold.dto.HouseHoldCreateRequestDto;
import com.habin.payhere_task.house_hold.dto.HouseHoldUpdateRequestDto;
import com.habin.payhere_task.house_hold.entity.HouseHold;
import org.mapstruct.*;

@Mapper(
		componentModel = "spring",
		uses = {EntityMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface HouseHoldMapper {
	@Mapping(target = "houseHoldId", ignore = true)
	@Mapping(target = "isDeleted", constant = "false")
	@Mapping(target = "user", source = "userEmail")
	HouseHold createDtoToEntity(HouseHoldCreateRequestDto dto);

	@Mapping(target = "isDeleted", ignore = true)
	@Mapping(target = "user", ignore = true)
	HouseHold.HouseHoldBuilder<?, ?> updateDtoToEntity
			(HouseHoldUpdateRequestDto dto, @MappingTarget HouseHold.HouseHoldBuilder<?,?> toBuilder);
}
