package com.walterjwhite.examples.practice.stack.set;

import com.walterjwhite.examples.practice.problems.stacks.set.SetOfStacks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetOfStacksTest {
  @Test
  void test_1() {
    final SetOfStacks setOfStacks = new SetOfStacks(3);
    setOfStacks.push(1);
    setOfStacks.push(2);
    setOfStacks.push(3);
    setOfStacks.push(4);
    setOfStacks.push(5);
    setOfStacks.push(6);

    Assertions.assertEquals(6, setOfStacks.pop());
    Assertions.assertEquals(5, setOfStacks.pop());
    Assertions.assertEquals(4, setOfStacks.pop());
    Assertions.assertEquals(3, setOfStacks.pop());
    Assertions.assertEquals(2, setOfStacks.pop());
    Assertions.assertEquals(1, setOfStacks.pop());
    Assertions.assertEquals(null, setOfStacks.pop());
  }

  @Test
  void test_2() {
    final SetOfStacks setOfStacks = new SetOfStacks(3);
    setOfStacks.push(1);
    setOfStacks.push(2);
    setOfStacks.push(3);
    setOfStacks.push(4);
    setOfStacks.push(5);
    setOfStacks.push(6);

    Assertions.assertEquals(6, setOfStacks.pop());
    Assertions.assertEquals(5, setOfStacks.pop());
    Assertions.assertEquals(4, setOfStacks.pop());
    Assertions.assertEquals(3, setOfStacks.pop());
    setOfStacks.push(4);
    Assertions.assertEquals(4, setOfStacks.pop());

    Assertions.assertEquals(2, setOfStacks.pop());
    Assertions.assertEquals(1, setOfStacks.pop());
    Assertions.assertEquals(null, setOfStacks.pop());
  }



}
