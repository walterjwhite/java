package com.walterjwhite.examples.practice.problems.organize.linkedlist;

@Deprecated
public class Sum {
  private Sum() {}

  public static SinglyLinkedList sumReverse(SinglyLinkedList a, SinglyLinkedList b) {
    final SinglyLinkedList result = a;

    int remainder = 0;
    while (true) {
      final int sum = a.getValue() + b.getValue() + remainder;

      a.setValue(sum % 10);
      remainder = sum / 10;

      if (a.getNext() == null) {
        break;
      }
      if (b.getNext() == null) {
        break;
      }

      a = a.getNext();
      b = b.getNext();
    }

    return result;
  }

}
