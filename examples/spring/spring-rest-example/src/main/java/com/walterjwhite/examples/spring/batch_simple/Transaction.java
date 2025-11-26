package com.walterjwhite.examples.spring.batch_simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    protected String xid;
    protected String orderId;
    protected String cusip;
    protected LocalDate transactionDate;
    protected TransactionType transactionType;
    protected TransactionStatus transactionStatus;
    protected double quantity;
    protected double pricePerShare;
    protected double commissionFee;
    protected String brokerId;
    protected String clientId;
    protected LocalDate settlementDate;
}
