package com.walterjwhite.examples.practice.problems.tree;

// given 2-d array, determine the maximum # of connected colors
// assumption #1: connected horizontally or vertically, not diagonally
// assumption #2: operating on a small grid (need not worry about stack depth)
// assumption #3: use an int to represent the color
// assumption #4: each row has the same # of columns (ie. grid is rectangular, it would be fun if it
// weren't, though I don't want to think about that now)
// assumption #5: we can have multiple groups of the same color as long as they are not connected
// assumption #6: colors are positive ints
// option #1: operating on a large grid ...
// fact #1: a cell can only belong to one group
public interface FillCounter {
  int count(final int[][] data);
}
