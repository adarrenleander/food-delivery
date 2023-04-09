package com.soa.fooddelivery.dispatch.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Dispatch {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
