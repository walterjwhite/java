package com.walterjwhite.examples.spring.mock;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
