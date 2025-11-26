package com.walterjwhite.examples.practice.problems.organize;

public class MinimumMaximumDistance {
  public static void main(final String[] args) {}

  public int minimumMaximum(final boolean[][] data) {
    final int result[][] = init(data.length, data[0].length + 1);

    ascending(data, result);

    descending(data, result);

    return min(result);
  }

  private void ascending(final boolean[][] data, final int[][] result) {
    for (int i = 1; i < data.length; i++) {
      for (int j = 0; j < data[i].length; j++) {
        if (data[i][j]) {
          result[i][j] = 0;
        } else {
          if (result[i - 1][j] < Integer.MAX_VALUE) {
            result[i][j] = result[i - 1][j] + 1;
          }
        }
      }
    }

    result[result.length - 1][result[0].length - 1] = max(result[result.length - 1]);
  }

  private void descending(final boolean[][] data, final int[][] result) {
    for (int i = data.length - 2; i >= 0; i--) {
      for (int j = 0; j < data[i].length; j++) {
        if (data[i][j]) {
          result[i][j] = 0;
        } else {
          if (result[i + 1][j] + 1 < result[i][j]) {
            result[i][j] = result[i + 1][j] + 1;
          }
        }
      }

      result[i][result[i].length - 1] = max(result[i]);
    }
  }

  private int[][] init(final int length, final int width) {
    final int[][] result = new int[length][width];
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < width; j++) {
        result[i][j] = Integer.MAX_VALUE;
      }
    }

    return result;
  }

  private int max(final int[] dataRow) {
    int max = 0;
    for (int i = 0; i < dataRow.length - 1; i++) {
      if (dataRow[i] == Integer.MAX_VALUE) {
        throw new IllegalStateException("Data not initialized: " + i);
      }

      if (dataRow[i] > max) {
        max = dataRow[i];
      }
    }

    return max;
  }

  private int min(final int[][] dataRow) {
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < dataRow.length; i++) {
      if (dataRow[i][dataRow[0].length - 1] == Integer.MAX_VALUE) {
        throw new IllegalStateException("Data not initialized: " + i);
      }

      if (dataRow[i][dataRow[0].length - 1] < min) {
        min = dataRow[i][dataRow[0].length - 1];
      }
    }

    return min;
  }
}
