package com.habin.payhere_task.house_hold.entity;

import com.habin.payhere_task.common.entity.BaseEntity;
import com.habin.payhere_task.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class HouseHold extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Comment("가계부 내역 ID")
    private Long houseHoldId;

    @Column(nullable = false, length = 30)
    @Comment("카테고리")
    private String category;

    @Column(nullable = false, length = 30)
    @Comment("결제 수단")
    private String payment;

    @Column(nullable = false, length = 10)
    @Comment("금액")
    private Integer amount;

    @Column(length = 500)
    @Comment("메모")
    private String memo;

    @Column(nullable = false)
    @Comment("삭제 여부")
    private Boolean isDeleted;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userEmail", referencedColumnName = "email")
    private User user;

}
