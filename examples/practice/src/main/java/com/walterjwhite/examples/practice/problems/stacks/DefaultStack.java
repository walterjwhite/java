package com.walterjwhite.examples.practice.problems.stacks;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

// LIFO
// each operation must run in O(1) time
// push - add element
// pop  - remove element
// min  - get current minimum element
// example:
// push 1 [1], min=1
// push 2 [1][2], min=1
// push 5 [1][2][5], min=1
// pop    [1][2], min=1
// pop    [1], min=1
// pop
// store min
// upon push/pop, adjust min

@AllArgsConstructor
@NoArgsConstructor
public class DefaultStack<Type> implements Stack<Type> {
  protected Node<Type> tail;

  public void push(final Type value) {
    final Node<Type> newTail = new Node<>(value, tail);
    tail = newTail;
  }

  public Type pop() {
    if (tail == null) {
      return null;
    }

    final Type rValue = tail.value;
    tail = tail.next;

    return rValue;
  }
}
