package com.walterjwhite.examples.practice;

import com.walterjwhite.examples.practice.problems.tree.DFSFillCounter;
import com.walterjwhite.examples.practice.problems.tree.IteratorFillCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IteratorFillCounterTest {
  protected int[][] init_1() {
    return new int[][] {
      {2, 2, 1, 0},
      {2, 1, 3, 1},
      {3, 3, 3, 3}
    };
  }

  @Test
  void test_iterator_1() {
    final int[][] data = init_1();

    Assertions.assertEquals(5, new IteratorFillCounter().count(data));
  }

  @Test
  void test_dfs_1() {
    final int[][] data = init_1();

    Assertions.assertEquals(5, new DFSFillCounter().count(data));
  }
}
