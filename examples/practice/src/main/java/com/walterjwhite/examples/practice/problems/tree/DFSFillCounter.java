package com.walterjwhite.examples.practice.problems.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import lombok.RequiredArgsConstructor;

// 2. use a HashMap to map color to the connected cells
// iterate through the array
// if the color is already in the map, check if this cell is connected, if not, add this under a
// separate count? -> NO
// or
// start @ 0,0, get (valid) neighbors, exclude out of bounds, visited before
// time/space complexity? -> visit each node once, O(m*n), space?
public class DFSFillCounter implements FillCounter {
  @RequiredArgsConstructor
  class Cell {
    final int x;
    final int y;
  }

  @Override
  public int count(final int[][] data) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[i].length; j++) {
        if (wasTraversed(data, i, j)) {
          continue;
        }

        final int currentColor = data[i][j];
        int currentCount = 1;

        final Queue<Cell> neighbors = new LinkedList<>();
        neighbors.addAll(getNeighbors(data, i, j, currentColor));

        // mark as traversed, limit additional data storage
        data[i][j] = -1;
        while (neighbors.peek() != null) {
          final Cell neighbor = neighbors.remove();
          neighbors.addAll(getNeighbors(data, neighbor.x, neighbor.y, currentColor));
          currentCount++;
        }

        if (currentCount > max) {
          max = currentCount;
        }
      }
    }

    return max;
  }

  protected boolean wasTraversed(final int[][] data, final int i, final int j) {
    return (data[i][j] < 0);
  }

  protected Vector<Cell> getNeighbors(
      final int[][] data, final int i, final int j, final int color) {
    final Vector<Cell> neighbors = new Vector<>();

    if (i > 0 && data[i - 1][j] == color) {
      neighbors.add(new Cell(i - 1, j));
      data[i - 1][j] = -1;
    }
    if (i + 1 < data.length && data[i + 1][j] == color) {
      neighbors.add(new Cell(i + 1, j));
      data[i + 1][j] = -1;
    }

    if (j > 0 && data[i][j - 1] == color) {
      neighbors.add(new Cell(i, j - 1));
      data[i][j - 1] = -1;
    }
    if (j + 1 < data[i].length && data[i][j + 1] == color) {
      neighbors.add(new Cell(i, j + 1));
      data[i][j + 1] = -1;
    }

    return neighbors;
  }
}
