package com.soa.fooddelivery.promotion.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table
public class User {
    @Id
    @Column(unique = true)
    private Integer id;
    private String code;
    private Float discount;
    private Integer loyaltyPoint;
    private Boolean activeStatus;
}
