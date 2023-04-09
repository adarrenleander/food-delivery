package com.soa.fooddelivery.order.repository;

import com.soa.fooddelivery.order.entity.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, String> {

}
