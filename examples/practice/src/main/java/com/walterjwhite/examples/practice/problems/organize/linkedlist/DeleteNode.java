package com.walterjwhite.examples.practice.problems.organize.linkedlist;

public class DeleteNode {
  private DeleteNode() {}

  public static void delete(final SinglyLinkedList nodeToDelete) {
    shift(nodeToDelete);
  }

  private static SinglyLinkedList shift(final SinglyLinkedList node) {
    if (node.getNext() == null) {
      node.setValue(-1);
      return null;
    }

    node.setValue(node.getNext().getValue());
    node.setNext(shift(node.getNext()));

    return node;
  }
}
