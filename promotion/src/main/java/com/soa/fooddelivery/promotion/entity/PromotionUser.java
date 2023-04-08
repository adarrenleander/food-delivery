package com.soa.fooddelivery.promotion.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table
public class PromotionUser {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Promotion promotion;
    private String code;
    private Float discount;
    private Integer loyaltyPoint;
    private Boolean activeStatus;
}
