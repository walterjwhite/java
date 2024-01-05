package com.walterjwhite.examples.practice.problems.organize;

/**
 * Problem Statement: Each block has an apartment which may have many resources located @ that
 * block. We want to minimize the maximum distance we would have to reach to accommodate all of the
 * user's goals:
 *
 * <p>Data: 0: {gym: true, school: false, library: true} 1: {gym: false, school: true, library:
 * false} 2: {gym: true, school: false, library: true} [0][false, true, false] [1][true, false,
 * false] [2][true, true, false] [3][false, true, false] [4][false, true, true]
 *
 * <p>Preference: {coffee, gym, library}
 *
 * <p>Assumptions: 1. use a simple boolean to indicate whether a preference is present 2. the # of
 * preferences matches (and is in the same order) as the block mapping
 *
 * <p>Algorithm / implementation: 1. initialize result (length for each preference + 1 (max)) int[]
 * result = new int[blocks.length][preferences.length + 1];
 *
 * <p>2. iterate through blocks in ascending order if present, distance is 0, else, previous
 * distance + 1, if unset then * 3. iterate through blocks in descending order ** ascending pass 4.
 * iterate through the result and take the minimum 1 [0][*,0,*,*] [1][0,1,*,*] [2][0,0,*,*]
 * [3][1,0,*,*] [4][2,0,0,*]
 *
 * <p>** descending pass [0][1,0,4,4] [1][0,1,3,3] [2][0,0,2,2] [3][1,0,1,1] [4][2,0,0,2]max(2,0,0)
 *
 * <p>1. list all assumptions a. must be more than 1 row b. assume convenience methods min, max of
 * array 2. list proposed algorithm better 3. list runtime / space complexity runtime: m + n space:
 * m*n + 1 (m*n) 4. cleanup move min and max functions to separate class (for readability) move
 * ascending / descending to functions (for readability)
 */
public class MinimumMaximumDistance {
  public static void main(final String[] args) {}

  public int minimumMaximum(final boolean[][] data) {
    // init result (1 additional column to store max)
    final int result[][] = init(data.length, data[0].length + 1);

    ascending(data, result);

    // descending
    descending(data, result);

    return min(result);
  }

  private void ascending(final boolean[][] data, final int[][] result) {
    for (int i = 1; i < data.length; i++) {
      for (int j = 0; j < data[i].length; j++) {
        if (data[i][j]) {
          result[i][j] = 0;
        } else {
          // if previous value != MAX
          if (result[i - 1][j] < Integer.MAX_VALUE) {
            result[i][j] = result[i - 1][j] + 1;
          }
        }
      }
    }

    // set last row
    result[result.length - 1][result[0].length - 1] = max(result[result.length - 1]);
  }

  private void descending(final boolean[][] data, final int[][] result) {
    for (int i = data.length - 2; i >= 0; i--) {
      for (int j = 0; j < data[i].length; j++) {
        if (data[i][j]) {
          result[i][j] = 0;
        } else {
          // if it is closer to go to the left
          if (result[i + 1][j] + 1 < result[i][j]) {
            result[i][j] = result[i + 1][j] + 1;
          }
        }
      }

      // set max for the row (block)
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
