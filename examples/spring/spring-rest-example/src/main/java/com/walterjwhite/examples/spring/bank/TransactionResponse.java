package com.walterjwhite.examples.spring.bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionResponse {
  private Long id;
  private LocalDate date;
  private String description;
  private BigDecimal amount;
  private TransactionStatus status;
  private Long userId;
}
