package com.walterjwhite.google.guice.resilience4j.circuitbreaker;

import com.walterjwhite.google.resilience4j.annotation.CircuitBreaker;

public class ImplementationExampleTest {

  @CircuitBreaker
  public String alwaysFail() {
    if (true) {
      throw new RuntimeException("testing");
    }

    return ("success");
  }
}
