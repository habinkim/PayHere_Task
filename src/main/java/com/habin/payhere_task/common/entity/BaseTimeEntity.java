package com.habin.payhere_task.common.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.habin.payhere_task.common.json.CustomLocalDateTimeDeserializer;
import com.habin.payhere_task.common.json.CustomLocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@ToString
@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BaseTimeEntity {

	@CreatedDate
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@Column(updatable = false)
	@Comment("등록 일시")
	private LocalDateTime insDtm;

	@LastModifiedDate
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@Column
	@Comment("수정 일시")
	private LocalDateTime updDtm;

}
