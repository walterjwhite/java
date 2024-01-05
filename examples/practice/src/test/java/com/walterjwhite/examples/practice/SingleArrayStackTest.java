package com.walterjwhite.examples.practice;

import com.walterjwhite.examples.practice.problems.stacks.FixedEqualSizeSingleArrayStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SingleArrayStackTest {
  @Test
  void test() {
    final FixedEqualSizeSingleArrayStack defaultStack = new FixedEqualSizeSingleArrayStack(5);
    defaultStack.push(0, 1);
    defaultStack.push(0, 2);
    defaultStack.push(0, 3);

    Assertions.assertEquals(3, defaultStack.pop(0));
    Assertions.assertEquals(2, defaultStack.pop(0));
    Assertions.assertEquals(1, defaultStack.pop(0));
    Assertions.assertEquals(null, defaultStack.pop(0));
  }

  @Test
  void testThrows() {
    final FixedEqualSizeSingleArrayStack defaultStack = new FixedEqualSizeSingleArrayStack(2);
    defaultStack.push(0, 1);
    defaultStack.push(0, 2);
    try {
      defaultStack.push(0, 3);
      Assertions.fail("Expected exception");
    } catch (StackOverflowError e) {
      // expected
    }

    Assertions.assertEquals(2, defaultStack.pop(0));
    Assertions.assertEquals(1, defaultStack.pop(0));
    Assertions.assertEquals(null, defaultStack.pop(0));
  }

  @Test
  void testabc() {
    final FixedEqualSizeSingleArrayStack defaultStack = new FixedEqualSizeSingleArrayStack(2);
    defaultStack.push(0, 1);
    defaultStack.push(0, 2);
    defaultStack.push(1, 3);
    defaultStack.push(1, 4);
    defaultStack.push(2, 5);
    defaultStack.push(2, 6);

    Assertions.assertEquals(2, defaultStack.pop(0));
    Assertions.assertEquals(1, defaultStack.pop(0));
    Assertions.assertEquals(null, defaultStack.pop(0));

    Assertions.assertEquals(4, defaultStack.pop(1));
    Assertions.assertEquals(3, defaultStack.pop(1));
    Assertions.assertEquals(null, defaultStack.pop(1));

    Assertions.assertEquals(6, defaultStack.pop(2));
    Assertions.assertEquals(5, defaultStack.pop(2));
    Assertions.assertEquals(null, defaultStack.pop(2));
  }
}
