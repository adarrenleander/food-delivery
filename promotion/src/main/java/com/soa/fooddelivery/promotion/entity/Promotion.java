package com.soa.fooddelivery.promotion.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table
public class Promotion {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private Float discount;
    private Integer loyaltyPoint;
    private Boolean activeStatus;
}
