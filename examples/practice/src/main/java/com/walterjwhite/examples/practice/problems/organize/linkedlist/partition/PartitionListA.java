package com.walterjwhite.examples.practice.problems.organize.linkedlist.partition;

import com.walterjwhite.examples.practice.problems.organize.linkedlist.SinglyLinkedList;

@Deprecated
public class PartitionListA implements Partition {
  public SinglyLinkedList partition(final SinglyLinkedList input, final int value) {
    SinglyLinkedList less = null;
    SinglyLinkedList greater = null;

    SinglyLinkedList current = input;
    if (input == null) {
      return null;
    }

    while (current != null) {
      if (current.getValue() <= value) {
        if (less != null) {
          less.setNext(less);
        }

        less = current;
      } else {
        if (greater != null) {
          greater.setNext(current);
        }

        greater = current;
      }

      current = current.getNext();
    }

    if (less != null) {
      input.setValue(less.getValue());
      input.setNext(less.getNext());
    } else {
      input.setValue(greater.getValue());
      input.setNext(greater.getNext());
    }

    return input;
  }

  void assemble() {}
}
