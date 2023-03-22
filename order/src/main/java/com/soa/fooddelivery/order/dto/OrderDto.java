package com.soa.fooddelivery.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * {
 *   "orderId": "xxx",
 *   "userId": "xxx",
 *   "restaurantId": "xxx",
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

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    private String orderId;
    private String userId;
    private String restaurantId;
    private String status;  // placed / completed / canceled
    private Float totalAmount;
    private OrderItemDto[] orders;
    private DeliveryDto delivery;
}
