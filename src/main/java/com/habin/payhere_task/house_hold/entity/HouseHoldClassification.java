package com.habin.payhere_task.house_hold.entity;

import com.habin.payhere_task.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import static jakarta.persistence.GenerationType.IDENTITY;

@SuperBuilder(toBuilder = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(length = 30)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public abstract class HouseHoldClassification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Comment("내역 분류 ID")
    @Column(length = 10)
    private Integer houseHoldClassificationId;

}
