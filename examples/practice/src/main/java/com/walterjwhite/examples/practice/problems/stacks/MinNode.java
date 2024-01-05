package com.walterjwhite.examples.practice.problems.stacks;

public class MinNode extends Node<Integer> {
  protected final Integer min;

  public MinNode(Integer value, Node<Integer> next) {
    super(value, next);

    if (next != null) {
      min = Math.min(next.value, value);
    } else {
      min = value;
    }
  }
}
