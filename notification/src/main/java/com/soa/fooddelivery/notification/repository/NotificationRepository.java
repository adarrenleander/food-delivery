package com.soa.fooddelivery.notification.repository;

import com.soa.fooddelivery.notification.entity.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, String> {

}
