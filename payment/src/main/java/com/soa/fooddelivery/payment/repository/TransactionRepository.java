package com.soa.fooddelivery.payment.repository;

import com.soa.fooddelivery.payment.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {
    @Query("SELECT t FROM Transaction t WHERE t.id=:id and t.transactionType=:transactionType")
    Transaction findByIdAndType(@Param("id") Integer id, @Param("transactionType") String transactionType);

    List<Transaction> findAllById(@Param("id") Integer id);

    @Query("SELECT t FROM Transaction t WHERE t.orderId=:orderId and t.transactionType=:transactionType")
    List<Transaction> findByOrderIdAndType(@Param("orderId") Integer orderId, @Param("transactionType") String transactionType);
}
