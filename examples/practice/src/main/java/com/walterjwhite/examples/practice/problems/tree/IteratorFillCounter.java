package com.walterjwhite.examples.practice.problems.tree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import lombok.Data;
import lombok.RequiredArgsConstructor;

public class IteratorFillCounter implements FillCounter {
  public int count(final int[][] colorGrid) {
    final Set<Group> groups = new HashSet<>();
    for (int i = 0; i < colorGrid.length; i++) {
      for (int j = 0; j < colorGrid[i].length; j++) {
        final Group existingGroup = find(colorGrid[i][j], colorGrid, groups, i, j);
        if (existingGroup == null) {
          final Group group = new Group(colorGrid[i][j]);
          group.cells.add(new Cell(i, j));
          groups.add(group);
        } else {
          existingGroup.cells.add(new Cell(i, j));
        }
      }
    }

    consolidate(colorGrid, groups);
    return max(groups);
  }

  static Group find(
      final int color, final int[][] colorGrid, final Set<Group> groups, final int x, final int y) {
    for (final Group group : groups) {
      if (group.color == color && group.isConnected(colorGrid, x, y)) {
        return group;
      }
    }

    return null;
  }

  static void consolidate(final int[][] colorGrid, final Set<Group> groups) {
    final Iterator<Group> groupIterator = groups.iterator();
    while (groupIterator.hasNext()) {
      final Group group = groupIterator.next();

      for (final Group otherGroup : groups) {
        if (group.equals(otherGroup)) {
          continue;
        }

        if (group.color == otherGroup.color) {
          final Iterator<Cell> cellIterator = group.cells.iterator();
          while (cellIterator.hasNext()) {
            final Cell cell = cellIterator.next();
            if (otherGroup.isConnected(colorGrid, cell.x, cell.y)) {
              group.cells.addAll(otherGroup.cells);
              otherGroup.cells.clear();

              break;
            }
          }
        }
      }
    }
  }

  static int max(final Set<Group> groups) {
    int max = Integer.MIN_VALUE;
    for (final Group group : groups) {
      if (group.cells.size() > max) {
        max = group.cells.size();
      }
    }

    return max;
  }

  @Data
  class Cell {

    final int x;
    final int y;
  }

  @RequiredArgsConstructor
  class Group {
    final Set<Cell> cells = new HashSet<>();
    final int color;

    boolean isConnected(final int[][] colorGrid, final int x, final int y) {
      final Set<Cell> possibleNeighbors = new HashSet<>();
      if (x > 0) {
        possibleNeighbors.add(new Cell(x - 1, y));
      }
      if (x + 1 < colorGrid.length) {
        possibleNeighbors.add(new Cell(x + 1, y));
      }

      if (y > 0) {
        possibleNeighbors.add(new Cell(x, y - 1));
      }
      if (y + 1 < colorGrid[0].length) {
        possibleNeighbors.add(new Cell(x, y + 1));
      }

      for (final Cell possibleNeighbor : possibleNeighbors) {
        if (cells.contains(possibleNeighbor)) {
          return true;
        }
      }

      return false;
    }
  }
}
