package com.soa.fooddelivery.user.entity;

import com.soa.fooddelivery.user.dto.UserDto;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table
public class User {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String category; //customer,driver
    private Boolean activeStatus;

    public UserDto convertToDto(){
        return new UserDto(id, firstName, lastName, category, activeStatus);
    }
}
