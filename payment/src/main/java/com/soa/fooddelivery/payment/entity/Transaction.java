package com.soa.fooddelivery.payment.entity;

import com.soa.fooddelivery.payment.dto.TransactionStatusDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Transaction {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer orderId;
    private String transactionType;
    private Float totalAmount;
    private String status;

    public TransactionStatusDto convertToDto() {
        return new TransactionStatusDto(id, userId, orderId, transactionType, totalAmount, status);
    }
}
