package com.walterjwhite.examples.practice.problems.stacks.set;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Stack {
  protected Node tail;
  protected Stack next;

  public Stack(Stack next) {
    this.next = next;
  }

  public void push(final Integer value) {
    final Node newTail = new Node(value, tail);
    tail = newTail;
  }

  public Integer pop() {
    if (tail == null) {
      return null;
    }

    final Integer rValue = tail.value;
    tail = tail.next;

    return rValue;
  }
}
