package com.habin.payhere_task.house_hold.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@SuperBuilder(toBuilder = true)
@Entity
@DiscriminatorValue("SPENDING")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpendingCategory extends HouseHoldClassification {

    @Column(nullable = false, length = 50)
    @Comment("지출 카테고리명")
    private String spendingCategoryNm;

}
