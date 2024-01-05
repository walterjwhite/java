package com.walterjwhite.examples.practice.stack;

import com.walterjwhite.examples.practice.problems.stacks.DefaultStack;
import com.walterjwhite.examples.practice.problems.stacks.MinDefaultStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StackTest {
  @Test
  void test() {
    final DefaultStack<Integer> defaultStack = new DefaultStack();
    defaultStack.push(1);
    defaultStack.push(2);
    defaultStack.push(3);

    Assertions.assertEquals(3, defaultStack.pop());
    Assertions.assertEquals(2, defaultStack.pop());
    Assertions.assertEquals(1, defaultStack.pop());
    Assertions.assertEquals(null, defaultStack.pop());
  }

  @Test
  void testMin() {
    final MinDefaultStack stack = new MinDefaultStack();
    stack.push(1);
    stack.push(2);
    stack.push(3);

    Assertions.assertEquals(1, stack.min());
    Assertions.assertEquals(3, stack.pop());
    Assertions.assertEquals(1, stack.min());

    Assertions.assertEquals(2, stack.pop());
    Assertions.assertEquals(1, stack.min());

    Assertions.assertEquals(1, stack.pop());
    Assertions.assertEquals(null, stack.pop());
    Assertions.assertEquals(null, stack.min());
  }

  @Test
  void testMin2() {
    final MinDefaultStack stack = new MinDefaultStack();
    stack.push(3);
    stack.push(2);
    stack.push(1);

    Assertions.assertEquals(1, stack.min());
    Assertions.assertEquals(1, stack.pop());
    Assertions.assertEquals(2, stack.min());

    Assertions.assertEquals(2, stack.pop());
    Assertions.assertEquals(3, stack.min());

    Assertions.assertEquals(3, stack.pop());
    Assertions.assertEquals(null, stack.pop());
    Assertions.assertEquals(null, stack.min());
  }
}
