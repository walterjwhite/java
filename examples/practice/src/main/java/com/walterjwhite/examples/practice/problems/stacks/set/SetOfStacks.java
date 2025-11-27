package com.walterjwhite.examples.practice.problems.stacks.set;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SetOfStacks {
  protected final int maxLength;
  protected int length;
  protected Stack stack;

  public void push(Integer value) {
    if (length % maxLength == 0) {
      stack = new Stack(stack);
    }

    stack.push(value);
    length++;
  }

  public Integer pop() {
    if (length == 0) {
      return null;
    }

    length--;
    final Integer returnValue = stack.pop();
    if (returnValue == null) {
      if (stack.next != null) {
        stack = stack.next;
        return stack.pop();
      }
    }

    return returnValue;
  }

  public Integer popAt(final int subStack) {
    return -1;
  }
}
