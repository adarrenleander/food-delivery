package com.soa.fooddelivery.loyalty.entity;

import com.soa.fooddelivery.loyalty.dto.LoyaltyDto;
import com.soa.fooddelivery.loyalty.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table
public class Loyalty {
    @Id
    @Column(unique = true)
    private Integer userId;
    private Integer loyaltyPoint;

    public LoyaltyDto convertToDto(){
        UserDto user = new UserDto(userId);
        return new LoyaltyDto(user, loyaltyPoint);
    }
}
