package com.walterjwhite.examples.practice.problems.tree;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tree {
  protected int value;
  protected Tree left;
  protected Tree right;

  protected boolean visited = false;

  public Tree(int value) {
    this.value = value;
  }

  public Tree(int value, Tree left, Tree right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public Tree bfsSearch(final int value) {
    if (visited) {
      return null;
    }

    visited = true;
    if (this.value == value) {
      return this;
    }

    return null;
  }

  protected Tree bfsSearch(final int value, final int level) {
    return null;
  }

  public Tree dfsSearch(final int value) {
    if (visited) {
      return null;
    }

    visited = true;
    if (this.value == value) {
      return this;
    }

    if (left != null) {
      final Tree l = left.dfsSearch(value);
      if (l != null) {
        return l;
      }
    }

    if (right != null) {
      final Tree r = right.dfsSearch(value);
      if (r != null) {
        return r;
      }
    }

    return null;
  }
}
