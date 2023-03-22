package com.soa.fooddelivery.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * {
 *   "orderId": "xxx",
 *   "userId": "xxx",
 *   "restaurantId": "xxx",
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
 *   "totalAmount": 12,
 *   "delivery": {
 *     "name": "xxx",
 *     "address": "xxx",
 *     "phoneNumber": "xxx",
 *     "time": "yyyy-mm-dd hh:mm"
 *   }
 * }
 */

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequestDto {
    private String orderId;
    private String userId;
    private String restaurantId;
    private OrderDto[] orders;
    private Float totalAmount;
    private DeliveryDto delivery;
}
