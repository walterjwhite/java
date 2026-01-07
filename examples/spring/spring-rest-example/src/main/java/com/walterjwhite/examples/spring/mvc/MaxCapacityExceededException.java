package com.walterjwhite.examples.spring.mvc;

public class MaxCapacityExceededException extends RuntimeException {
  public MaxCapacityExceededException(String message) {
    super(message);
  }
}
