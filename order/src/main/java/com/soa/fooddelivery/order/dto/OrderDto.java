package com.soa.fooddelivery.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

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
    private String orderId;
    private String userId;
    private String restaurantId;
    private String promotionCode;
    private String status;  // created / placed / completed / canceled / failed
    private Float totalAmount;
    private OrderItemDto[] orders;
    private DeliveryDto delivery;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
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

    public OrderItemDto[] getOrders() {
        return orders;
    }

    public void setOrders(OrderItemDto[] orders) {
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
}
