package com.habin.payhere_task.common.jpa;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PredicateBuilder {

	private final List<Predicate> predicateBuilders = new ArrayList<>();

	public static PredicateBuilder builder() {
		return new PredicateBuilder();
	}

	public <P extends Predicate> PredicateBuilder and(P pr) {
		predicateBuilders.add(pr);
		return this;
	}

	public Predicate build() {
		return ExpressionUtils.allOf(predicateBuilders);
	}

	public PredicateBuilder eqString(StringPath column, String value) {
		if (StringUtils.hasText(value)) {
			predicateBuilders.add(column.eq(value));
		}

		return this;
	}

	public PredicateBuilder containsString(StringPath column, String value) {
		if (StringUtils.hasText(value)) {
			predicateBuilders.add(column.contains(value));
		}

		return this;
	}

	public PredicateBuilder containsStringDesc(StringPath column1, StringPath column2, String value) {
		if (StringUtils.hasText(value)) {
			predicateBuilders.add(new BooleanBuilder()
					.andAnyOf(column2.contains(value), column2.contains(value)));
		}

		return this;
	}

	public PredicateBuilder inString(StringPath column, List<String> value) {
		if (value != null) {
			predicateBuilders.add(column.in(value));
		}

		return this;
	}

	public <N extends Number & Comparable<?>> PredicateBuilder betweenNumberDynamic(NumberPath<N> column, N min, N max) {
		if (min != null) {
			predicateBuilders.add(column.goe(min));
		}

		if (max != null) {
			predicateBuilders.add(column.loe(max));
		}

		return this;
	}

	public <N extends Number & Comparable<?>> PredicateBuilder eqNumber(NumberPath<N> column, N value) {
		if (value != null) {
			predicateBuilders.add(column.eq(value));
		}

		return this;
	}

	public <N extends Number & Comparable<?>> PredicateBuilder neNumber(NumberPath<N> column, N value) {
		if (value != null) {
			predicateBuilders.add(column.ne(value));
		}

		return this;
	}

	public <N extends Number & Comparable<?>> PredicateBuilder inLong(NumberPath<N> column, List<N> valueList) {
		if (valueList != null) {
			predicateBuilders.add(column.in(valueList));
		}

		return this;
	}

	public <E extends Enum<E>> PredicateBuilder eqEnum(EnumPath<E> column, E value) {

		if (value != null) {
			predicateBuilders.add(column.eq(value));
		}

		return this;
	}

	public <E extends Enum<E>> PredicateBuilder inEnum(EnumPath<E> column, List<E> value) {

		if (value != null) {
			predicateBuilders.add(column.in(value));
		}

		return this;
	}

	public PredicateBuilder betweenDateTime(DateTimePath<LocalDateTime> column, LocalDateTime startDate, LocalDateTime endDate) {
		if (startDate != null && endDate != null) {
			predicateBuilders.add(column.between(startDate, endDate));
		}

		return this;
	}

	public PredicateBuilder betweenDateTimeDynamic(DateTimePath<LocalDateTime> column, LocalDateTime startDate, LocalDateTime endDate) {
		if (startDate != null) {
			predicateBuilders.add(column.goe(startDate));
		}

		if (endDate != null) {
			predicateBuilders.add(column.loe(endDate));
		}

		return this;
	}

	public PredicateBuilder betweenDate(DatePath<LocalDate> column, LocalDate startDate, LocalDate endDate) {
		if (startDate != null && endDate != null) {
			predicateBuilders.add(column.between(startDate, endDate));
		}

		return this;
	}

	public PredicateBuilder betweenDateDynamic(DatePath<LocalDate> column, LocalDate startDate, LocalDate endDate) {
		if (startDate != null) {
			predicateBuilders.add(column.goe(startDate));
		}

		if (endDate != null) {
			predicateBuilders.add(column.loe(endDate));
		}

		return this;
	}

	public PredicateBuilder eqDateTime(DateTimePath<LocalDateTime> column, LocalDateTime value) {

		if (value != null) {
			predicateBuilders.add(column.eq(value));
		}

		return this;
	}

	public PredicateBuilder eqDate(DatePath<LocalDate> column, LocalDate value) {

		if (value != null) {
			predicateBuilders.add(column.eq(value));
		}

		return this;
	}

}
