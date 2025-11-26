package com.walterjwhite.examples.practice.problems.stacks;

public class FixedEqualSizeSingleArrayStack {
  protected final int length;
  protected final int[] store;
  protected int a = -1, b = -1, c = -1;

  public FixedEqualSizeSingleArrayStack(final int maxLength) {
    this.length = maxLength;
    this.store = new int[3 * maxLength];
  }

  public void push(final int stack, final Integer value) {
    switch (stack) {
      case 0:
        if (a + 1 >= length) {
          throw new StackOverflowError(a + " - Cannot add to stack, a is full");
        }

        store[++a] = value;
        break;
      case 1:
        if (b + 1 >= 2 * length) {
          throw new StackOverflowError(b + " - Cannot add to stack, b is full");
        }

        store[++b + length] = value;
        break;
      case 2:
        if (c + 1 >= 3 * length) {
          throw new StackOverflowError(c + " - Cannot add to stack, c is full");
        }

        store[++c + 2 * length] = value;
        break;
      default:
        throw new IllegalArgumentException(stack + " is invalid");
    }
  }

  public Integer pop(final int stack) {
    switch (stack) {
      case 0:
        if (a >= 0) {
          return store[a--];
        }

        return null;
      case 1:
        if (b < length && b >= 0) {
          return store[b-- + length];
        }

        return null;
      case 2:
        if (c < length && c >= 0) {
          return store[c-- + 2 * length];
        }

        return null;
    }

    throw new IllegalArgumentException(stack + " is invalid");
  }
}
