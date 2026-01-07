package com.walterjwhite.examples.spring.bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TransactionRequest {
  private LocalDate date;
  private String description;
  private BigDecimal amount;

  public static void main(final String[] args) {}
}
