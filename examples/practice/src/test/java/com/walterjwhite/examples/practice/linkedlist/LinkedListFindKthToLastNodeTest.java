package com.walterjwhite.examples.practice.linkedlist;

import com.walterjwhite.examples.practice.problems.organize.linkedlist.SinglyLinkedList;
import com.walterjwhite.examples.practice.problems.organize.linkedlist.kthtolast.IteratingLinkedListFindKthToLastNode;
import com.walterjwhite.examples.practice.problems.organize.linkedlist.kthtolast.KthtoLastNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class LinkedListFindKthToLastNodeTest {
  //    protected final KthtoLastNode kthtoLastNode = new BufferedLinkedListFindKthToLastNode();
  protected final KthtoLastNode kthtoLastNode = new IteratingLinkedListFindKthToLastNode();

  @Test
  void testNull() {
    final SinglyLinkedList input = null;

    Assertions.assertEquals(-1, kthtoLastNode.find(input, 1));
  }

  @Test
  void test0() {
    final SinglyLinkedList input = new SinglyLinkedList();

    Assertions.assertEquals(-1, kthtoLastNode.find(input, 1));
  }

  @Test
  void test1() {
    final SinglyLinkedList input = new SinglyLinkedList();
    input.setNext(new SinglyLinkedList(2, null));

    Assertions.assertEquals(2, kthtoLastNode.find(input, 1));
  }

  @Test
  void test3_1() {
    final SinglyLinkedList input = new SinglyLinkedList();
    input.setNext(new SinglyLinkedList(2, new SinglyLinkedList(3, new SinglyLinkedList(4, null))));

    Assertions.assertEquals(4, kthtoLastNode.find(input, 1));
  }

  @Test
  void test3_2() {
    final SinglyLinkedList input = new SinglyLinkedList();
    input.setNext(new SinglyLinkedList(2, new SinglyLinkedList(3, new SinglyLinkedList(4, null))));

    Assertions.assertEquals(3, kthtoLastNode.find(input, 2));
  }

  @Test
  void test3_3() {
    final SinglyLinkedList input = new SinglyLinkedList();
    input.setNext(new SinglyLinkedList(2, new SinglyLinkedList(3, new SinglyLinkedList(4, null))));

    Assertions.assertEquals(2, kthtoLastNode.find(input, 3));
  }
}
