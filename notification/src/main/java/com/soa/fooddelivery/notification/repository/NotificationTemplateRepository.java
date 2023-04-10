package com.soa.fooddelivery.notification.repository;

import com.soa.fooddelivery.notification.entity.NotificationTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationTemplateRepository extends CrudRepository<NotificationTemplate, String> {
    List<NotificationTemplate> findAllById(@Param("id") Integer id);
}
