package com.soa.fooddelivery.restaurant.entity;

import com.soa.fooddelivery.restaurant.dto.MenuItemDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@NoArgsConstructor
@Table
public class MenuItem {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String detail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Menu menu;
    private Boolean activeStatus;
    private Float price;

    public MenuItemDto convertToDto(){
        return new MenuItemDto(id, name, price, detail, activeStatus);
    }
}
