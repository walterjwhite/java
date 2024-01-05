package com.walterjwhite.examples.practice.counter;

import com.walterjwhite.examples.practice.problems.organize.counter.AtomicCounter;
import com.walterjwhite.examples.practice.problems.organize.counter.Counter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StaticClassTest {
  protected final Counter counter = AtomicCounter.INSTANCE;

  @Test
  void testInit() {
    final int initialValue = counter.get();

    Assertions.assertEquals(initialValue, counter.get());
  }

  @Test
  void testDecrement() {
    final int initialValue = counter.get();

    Assertions.assertEquals(initialValue - 1, counter.decrement());
  }

  @Test
  void testIncrement() {
    final int initialValue = counter.get();

    Assertions.assertEquals(initialValue + 10, counter.increment());
  }
}
