package com.walterjwhite.examples.practice.problems.stacks;

// need to replace node with LocalMinNode
public class MinDefaultStack extends DefaultStack<Integer> {
  protected Integer min = Integer.MAX_VALUE;

  @Override
  public void push(final Integer value) {
    final MinNode newTail = new MinNode(value, tail);

    min = Math.min(min, value);
    tail = newTail;
  }

  @Override
  public Integer pop() {
    final Integer returnValue = super.pop();

    if (tail != null) {
      min = ((MinNode) tail).min;
    } else {
      min = null;
    }

    return returnValue;
  }

  public Integer min() {
    return min;
  }
}
