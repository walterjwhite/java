package com.walterjwhite.examples.practice.problems.organize.trees;

public class Trees {
  protected final Node root;

  public class Node {
    int value;
    Node left, right;

    // Node peer;

    Node(final int value) {
      this.value = value;
    }
  }

  //   12    12
  //  6   3  9
  // 5 7     12
  public Trees() {
    root = new Node(12);
    root.left = new Node(6);
    root.right = new Node(3);
    root.left.left = new Node(5);
    root.left.right = new Node(7);
  }

  public void print() {
    printLevel(0, root);
  }

  public void printLevel(final int level, final Node... nodes) {
    int sum = 0;
    //        Arrays.stream(nodes).forEach(node -> {
    //            if(node != null){
    //                sum += node.value;
    //            }
    //        });
    final Node[] children = new Node[nodes.length * 2];
    int i = 0;
    for (Node node : nodes) {
      if (node == null) continue;

      sum += node.value;
      if (node.left != null) children[i++] = node.left;
      if (node.right != null) children[i++] = node.right;
    }

    if (i > 0) {
      printLevel(level + 1, children);
    } else {
    }
  }
}
