package com.habin.payhere_task.category.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.habin.payhere_task.common.entity.BaseTimeEntity;
import com.habin.payhere_task.house_hold.entity.HouseHold;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    @Comment("카테고리 ID")
    private Integer id;

    @Column(nullable = false, length = 20)
    @Comment("카테고리 명")
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parentCategoryId", referencedColumnName = "id")
    @Comment("부모 카테고리 ID")
    @JsonBackReference
    private Category parent;

    @Builder.Default
    @OneToMany(mappedBy = "parent", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE
    }, orphanRemoval = true)
    @JsonManagedReference
    private List<Category> childs = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "category", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE
    }, orphanRemoval = true)
    @JsonManagedReference
    private List<HouseHold> houseHolds = new ArrayList<>();

}
