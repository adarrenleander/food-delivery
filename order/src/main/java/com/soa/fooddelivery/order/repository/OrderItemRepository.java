package com.soa.fooddelivery.order.repository;

import com.soa.fooddelivery.order.dto.OrderItemDto;
import com.soa.fooddelivery.order.entity.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, String> {
    @Query("SELECT new com.soa.fooddelivery.order.dto.OrderItemDto(o.menuItemId, o.quantity, o.notes, o.price) FROM OrderItem o WHERE o.order.id=:orderId")
    List<OrderItemDto> findAllByOrderId(@Param("orderId") Integer orderId);
}
