package com.walterjwhite.examples.practice.problems.organize.linkedlist.partition;

import com.walterjwhite.examples.practice.problems.organize.linkedlist.SinglyLinkedList;

@Deprecated
public class PartitionListB implements Partition {
  public SinglyLinkedList partition(final SinglyLinkedList input, final int value) {
    SinglyLinkedList next;

    SinglyLinkedList current = get(input, value, false);
    if (current == null) {
      return null;
    }

    boolean greater = false;
    while (current != null) {
      next = get(current.getNext(), value, greater);
      if (next == null) {
        greater = true;
        next = get(current.getNext(), value, greater);
        if (next == null) {
          return null;
        }
      }

      current.setNext(next);
      current = current.getNext();
    }

    return current;
  }

  private static SinglyLinkedList get(
      final SinglyLinkedList input, final int value, final boolean greater) {
    if (input == null) {
      return null;
    }

    if (!greater) {
      if (input.getValue() <= value) {
        return input;
      }

      return get(input.getNext(), value, greater);
    }

    if (input.getValue() > value) {
      return input;
    }

    return get(input.getNext(), value, greater);
  }
}
