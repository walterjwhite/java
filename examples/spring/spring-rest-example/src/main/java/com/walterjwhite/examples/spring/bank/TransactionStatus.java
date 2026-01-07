package com.walterjwhite.examples.spring.bank;

public enum TransactionStatus {
  NEW,
  VERIFIED,
  DENIED,
  DELETED,
  PENDING_DELETION,
  DENIED_DELETION;
}
