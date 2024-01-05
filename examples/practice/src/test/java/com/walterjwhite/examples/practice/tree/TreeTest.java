package com.walterjwhite.examples.practice.tree;

import com.walterjwhite.examples.practice.problems.tree.Tree;
import org.junit.jupiter.api.Test;

public class TreeTest {
  @Test
  void testTraversal() {
    init();
  }

  protected Tree init() {
    final Tree root = new Tree(0);
    root.setLeft(new Tree(1));
    root.getLeft().setLeft(new Tree(3));
    root.getLeft().setRight(new Tree(5));
    root.setRight(new Tree(34));
    root.getRight().setLeft(new Tree(2));
    root.getRight().setRight(new Tree(6));

    return root;
  }
}
