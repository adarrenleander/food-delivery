package com.soa.fooddelivery.order.repository;

import com.soa.fooddelivery.order.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
    List<Order> findAllById(@Param("id") Integer id);

    List<Order> findAllByUserId(@Param("userId") Integer userId);
}
