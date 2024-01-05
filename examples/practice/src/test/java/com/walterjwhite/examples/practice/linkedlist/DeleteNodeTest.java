package com.walterjwhite.examples.practice.linkedlist;

import com.walterjwhite.examples.practice.problems.organize.linkedlist.DeleteNode;
import com.walterjwhite.examples.practice.problems.organize.linkedlist.SinglyLinkedList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class DeleteNodeTest {
  @Test
  void testDelete1() {
    final SinglyLinkedList list = new SinglyLinkedList(1, new SinglyLinkedList(2, null));
    final SinglyLinkedList delete = list;
    LOGGER.debug("list: {}", list);

    DeleteNode.delete(delete);
    int size = 0;
    SinglyLinkedList i = list;
    while (i != null) {
      size++;
      i = i.getNext();
    }

    LOGGER.debug("list: {}", list);

    Assertions.assertEquals(1, size);
  }

  @Test
  void testDelete5() {
    final SinglyLinkedList list =
        new SinglyLinkedList(
            1,
            new SinglyLinkedList(
                2,
                new SinglyLinkedList(3, new SinglyLinkedList(4, new SinglyLinkedList(5, null)))));
    final SinglyLinkedList delete = list;
    LOGGER.debug("list: {}", list);

    DeleteNode.delete(delete);
    int size = 0;
    SinglyLinkedList i = list;
    while (i != null) {
      size++;
      i = i.getNext();
    }

    LOGGER.debug("list: {}", list);

    Assertions.assertEquals(4, size);
  }
}
