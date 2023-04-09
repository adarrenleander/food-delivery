package com.soa.fooddelivery.order.entity;

import com.soa.fooddelivery.order.dto.DeliveryDto;
import com.soa.fooddelivery.order.dto.OrderDto;
import com.soa.fooddelivery.order.dto.OrderItemDto;

import javax.persistence.*;

@Entity
@Table
public class Order {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer restaurantId;
    private String promotionCode;
    private String status;  // created / placed / completed / canceled / failed
    private Float totalAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Delivery delivery;

    public OrderDto convertToDto(){
        return new OrderDto(id, userId, restaurantId, promotionCode, status, totalAmount, null, delivery.convertToDto());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
