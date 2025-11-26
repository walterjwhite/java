package com.walterjwhite.examples.practice.problems.stacks;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


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
