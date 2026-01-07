package com.walterjwhite.examples.practice.problems.organize.linkedlist.partition;

import com.walterjwhite.examples.practice.problems.organize.linkedlist.SinglyLinkedList;

@Deprecated
public class PartitionListC implements Partition {
  public SinglyLinkedList partition(final SinglyLinkedList input, final int value) {
    SinglyLinkedList before = null;
    SinglyLinkedList after = null;
    SinglyLinkedList current = input;
    while (current != null) {
      final SinglyLinkedList next = current.getNext();

      if (current.getValue() < value) {
        current.setNext(before);
        before = current;
      } else {
        current.setNext(after);
        after = current;
      }

      current = next;
    }

    if (before == null) {
      return after;
    }

    current = before;
    while (current.getNext() != null) {
      current = current.getNext();
    }
    current.setNext(after);

    return before;
  }
}
