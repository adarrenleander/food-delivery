package com.soa.fooddelivery.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * {
 *   "orderId": "xxx",
 *   "userId": "xxx",
 *   "restaurantId": "xxx",
 *   "promotionCode": "xxx",
 *   "status": "placed",
 *   "totalAmount": 12,
 *   "orders": [
 *     {
 *       "menuItemId": "xxx",
 *       "quantity": 1,
 *       "notes": "xxx",
 *       "price": 2
 *     },
 *     {
 *       "menuItemId": "xxx",
 *       "quantity": 1,
 *       "notes": "xxx",
 *       "price": 10
 *     }
 *   ],
 *   "delivery": {
 *     "name": "xxx",
 *     "address": "xxx",
 *     "phoneNumber": "xxx",
 *     "time": "yyyy-mm-dd hh:mm"
 *   }
 * }
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    private Integer orderId;
    private Integer userId;
    private Integer restaurantId;
    private String promotionCode;
    private String status;  // created / placed / completed / canceled / failed
    private Float totalAmount;
    private List<OrderItemDto> orders;
    private DeliveryDto delivery;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public List<OrderItemDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItemDto> orders) {
        this.orders = orders;
    }

    public DeliveryDto getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryDto delivery) {
        this.delivery = delivery;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public OrderDto() {
    }

    public OrderDto(Integer orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public OrderDto(Integer orderId, Integer userId, Integer restaurantId, String promotionCode, String status, Float totalAmount, List<OrderItemDto> orders, DeliveryDto delivery) {
        this.orderId = orderId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.promotionCode = promotionCode;
        this.status = status;
        this.totalAmount = totalAmount;
        this.orders = orders;
        this.delivery = delivery;
    }
}
