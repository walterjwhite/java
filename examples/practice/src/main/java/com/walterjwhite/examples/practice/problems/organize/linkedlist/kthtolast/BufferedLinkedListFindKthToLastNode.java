package com.walterjwhite.examples.practice.problems.organize.linkedlist.kthtolast;

import com.walterjwhite.examples.practice.problems.organize.linkedlist.SinglyLinkedList;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BufferedLinkedListFindKthToLastNode implements KthtoLastNode {
  public int find(final SinglyLinkedList input, final int kthNode) {
    if (input == null) {
      return -1;
    }

    if (input.getNext() == null) {
      return -1;
    }

    final Integer[] buffer = new Integer[kthNode];
    SinglyLinkedList current = input;
    while (current != null) {
      push(buffer, current);

      current = current.getNext();
    }

    LOGGER.debug("queue: {}", Arrays.toString(buffer));

    return buffer[buffer.length - 1];
  }

  protected void push(final Integer[] buffer, final SinglyLinkedList input) {
    for (int i = buffer.length - 1; i > 0; i--) {
      buffer[i] = buffer[i - 1];
    }

    buffer[0] = input.getValue();
  }
}
