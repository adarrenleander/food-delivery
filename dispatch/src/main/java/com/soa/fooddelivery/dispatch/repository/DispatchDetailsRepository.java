package com.soa.fooddelivery.dispatch.repository;

import com.soa.fooddelivery.dispatch.entity.DispatchDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispatchDetailsRepository extends CrudRepository<DispatchDetails, String> {
    List<DispatchDetails> findAllById(@Param("id") Integer id);
}
