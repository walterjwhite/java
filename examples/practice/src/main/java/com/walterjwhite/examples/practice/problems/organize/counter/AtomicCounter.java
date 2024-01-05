package com.walterjwhite.examples.practice.problems.organize.counter;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AtomicCounter implements Counter {
  public static final AtomicCounter INSTANCE = new AtomicCounter();

  protected final AtomicInteger counter = new AtomicInteger(0);

  public int increment() {
    return counter.addAndGet(10);
  }

  public int decrement() {
    return counter.addAndGet(-1);
  }

  public int get() {
    return counter.get();
  }
}
