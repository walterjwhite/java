package com.walterjwhite.examples.practice.linkedlist;

import com.walterjwhite.examples.practice.problems.organize.linkedlist.SinglyLinkedList;
import com.walterjwhite.examples.practice.problems.organize.linkedlist.partition.Partition;
import com.walterjwhite.examples.practice.problems.organize.linkedlist.partition.PartitionListC;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class PartitionListTest {
  protected final Partition partition = new PartitionListC();

  @Test
  void testPartition2_1() {
    final SinglyLinkedList list = new SinglyLinkedList(1, new SinglyLinkedList(2));
    LOGGER.debug("list: {}", list);

    SinglyLinkedList result = partition.partition(list, 1);
    LOGGER.debug("list: {}", result);

    Assertions.assertEquals(2, result.count());
  }

  @Test
  void testPartition2_2() {
    final SinglyLinkedList list = new SinglyLinkedList(1, new SinglyLinkedList(1));
    LOGGER.debug("list: {}", list);

    SinglyLinkedList result = partition.partition(list, 1);
    LOGGER.debug("list: {}", result);

    Assertions.assertEquals(2, result.count());
  }

  @Test
  void testPartition2_3() {
    final SinglyLinkedList list = new SinglyLinkedList(3, new SinglyLinkedList(3));
    LOGGER.debug("list: {}", list);

    SinglyLinkedList result = partition.partition(list, 1);
    LOGGER.debug("list: {}", result);

    Assertions.assertEquals(2, result.count());
  }

  @Test
  void testPartition5() {
    final SinglyLinkedList list =
        new SinglyLinkedList(
            1,
            new SinglyLinkedList(
                6, new SinglyLinkedList(5, new SinglyLinkedList(3, new SinglyLinkedList(2)))));
    LOGGER.debug("list: {}", list);

    SinglyLinkedList result = partition.partition(list, 3);

    LOGGER.debug("list: {}", result);

    Assertions.assertEquals(5, result.count());
  }
}
