package com.habin.payhere_task.common.mapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;



@Component
public class ReferenceMapper {

	@PersistenceContext
	private EntityManager entityManager;

	@ObjectFactory
	public <T> T integerToEntity(@NonNull final Integer id, @TargetType Class<T> type) {
		return entityManager.getReference(type, id);
	}

	@ObjectFactory
	public <T> T longToEntity(@NonNull final Long id, @TargetType Class<T> type) {
		return entityManager.getReference(type, id);
	}

	@ObjectFactory
	public <T> T stringToEntity(@NonNull final String id, @TargetType Class<T> type) {
		return entityManager.getReference(type, id);
	}

}
