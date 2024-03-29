package com.soa.fooddelivery.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * {
 *   "menuItemId": "xxx",
 *   "quantity": 1,
 *   "notes": "xxx",
 *   "price": 2
 * }
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDto {
    private Integer menuItemId;
    private Integer quantity;
    private String notes;
    private Float price;

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public OrderItemDto(Integer menuItemId, Integer quantity, String notes, Float price) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.notes = notes;
        this.price = price;
    }
}
