package com.walterjwhite.examples.practice.problems.organize.counter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StaticCounter implements Counter {
  public static final StaticCounter INSTANCE = new StaticCounter();

  protected int counter = 0;

  public int increment() {
    return counter += 10;
  }

  public int decrement() {
    return counter -= 1;
  }

  public int get() {
    return counter;
  }
}
