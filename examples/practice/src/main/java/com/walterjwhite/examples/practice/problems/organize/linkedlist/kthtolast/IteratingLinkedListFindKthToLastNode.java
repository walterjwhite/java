package com.walterjwhite.examples.practice.problems.organize.linkedlist.kthtolast;

import com.walterjwhite.examples.practice.problems.organize.linkedlist.SinglyLinkedList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IteratingLinkedListFindKthToLastNode implements KthtoLastNode {
  public int find(final SinglyLinkedList input, final int kthNode) {
    if (input == null) {
      return -1;
    }

    if (input.getNext() == null) {
      return -1;
    }

    SinglyLinkedList current = input;
    while (current != null) {
      if (fromEnd(current) == kthNode) {
        return current.getValue();
      }

      current = current.getNext();
    }

    throw new IllegalArgumentException("Unable to find kth node: " + kthNode);
  }

  protected int fromEnd(final SinglyLinkedList input) {
    if (input.getNext() == null) {
      return 1;
    }

    return fromEnd(input.getNext()) + 1;
  }
}
