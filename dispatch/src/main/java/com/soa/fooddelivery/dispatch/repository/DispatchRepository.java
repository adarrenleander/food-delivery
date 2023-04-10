package com.soa.fooddelivery.dispatch.repository;

import com.soa.fooddelivery.dispatch.entity.Dispatch;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface DispatchRepository extends CrudRepository<Dispatch, String> {
    List<Dispatch> findAllById(@Param("id") Integer id);
}
