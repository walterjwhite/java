package com.walterjwhite.examples.practice.linkedlist;

import com.walterjwhite.examples.practice.problems.organize.linkedlist.SinglyLinkedList;
import com.walterjwhite.examples.practice.problems.organize.linkedlist.Sum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SumTest {
  @Test
  void testSum2() {
    final SinglyLinkedList a = new SinglyLinkedList(1, new SinglyLinkedList(2, null));
    final SinglyLinkedList b = new SinglyLinkedList(3, new SinglyLinkedList(7, null));
    LOGGER.debug("list: {} + {}", a, b);

    final SinglyLinkedList result = Sum.sumReverse(a, b);

    LOGGER.debug("result: {}", result);

  }

  @Test
  void testSum3() {
    final SinglyLinkedList a =
        new SinglyLinkedList(7, new SinglyLinkedList(1, new SinglyLinkedList(6, null)));
    final SinglyLinkedList b =
        new SinglyLinkedList(5, new SinglyLinkedList(9, new SinglyLinkedList(2, null)));
    LOGGER.debug("list: {} + {}", a, b);

    final SinglyLinkedList result = Sum.sumReverse(a, b);

    LOGGER.debug("result: {}", result);

  }
}
