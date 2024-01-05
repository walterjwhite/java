package com.walterjwhite.examples.practice.problems.stacks.set;

import lombok.RequiredArgsConstructor;

/**
 * Summary: 1. fixed stack length / height 2. once length / height is reached, a new sub-stack is
 * created 3. all operations are transparent to user
 *
 * <p>Assumptions: 1. not thread-safe 2. working with ints
 *
 * <p>Design 1. implement standard stack 2. for set of stacks, use a stack to store the stacks 3.
 * when one stack fills up, create a new stack and point to that, store a pointer to the previous
 * stack a. init with max length b. use a stack to refer to the sub-stack, all operations are
 * performed against that sub-stack
 */
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
    // this operation will break the current functionality, need to implement a separate set of
    // stacks for this
    return -1;
  }
}
