package com.soa.fooddelivery.restaurant.entity;
import com.soa.fooddelivery.restaurant.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table
public class Restaurant {


    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;

    public RestaurantDto convertToDto(){
        return new RestaurantDto(id, name, address);
    }
}
