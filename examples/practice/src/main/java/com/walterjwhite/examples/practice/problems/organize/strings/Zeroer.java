package com.walterjwhite.examples.practice.problems.organize.strings;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Zeroer {
  private Zeroer() {}

  @Getter
  @RequiredArgsConstructor
  private static class Point {
    protected final int i;
    protected final int j;
  }

  public static void zero(final int[][] matrix) {
    if (matrix == null) {
      return;
    }

    if (matrix.length == 1 && matrix[0].length == 1) {
      return;
    }

    final Set<Point> points = new HashSet<>();

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] == 0) {
          //                    zeroRow(matrix, i, j);
          //                    zeroColumn(matrix, i, j);
          points.add(new Point(i, j));
        }
      }
    }

    points.stream()
        .forEach(
            p -> {
              zeroRow(matrix, p.getI(), p.getJ());
              zeroColumn(matrix, p.getI(), p.getJ());
            });
  }

  private static void zeroRow(final int[][] matrix, int i, int j) {
    for (j = 0; j < matrix[i].length; j++) {
      matrix[i][j] = 0;
    }
  }

  private static void zeroColumn(final int[][] matrix, int i, int j) {
    for (i = 0; i < matrix.length; i++) {
      matrix[i][j] = 0;
    }
  }
}
