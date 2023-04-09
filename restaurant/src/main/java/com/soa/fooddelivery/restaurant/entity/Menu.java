package com.soa.fooddelivery.restaurant.entity;

import com.soa.fooddelivery.restaurant.dto.MenuDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@NoArgsConstructor
@Table
public class Menu {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String detail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Restaurant restaurant;
    private Boolean activeStatus;

    public MenuDto convertToDto(){

        return new MenuDto(id, name, detail, activeStatus);
    }


}
