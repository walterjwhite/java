package com.walterjwhite.examples.practice.problems.google;

import java.util.Arrays;

// break the list up into chunks, keep track of distinct elements
public class Google3 implements GoogleProblem {

  public int missing(int x[], int y[]) {

    //        for(Arrays.stream(x).parallel())
    //        Arrays.stream(x).reduce((op) -> (applyAsInt()))
    int missing = Arrays.stream(x).reduce(0, (a, b) -> a + b);
    return missing;
  }

  //    @FunctionalInterface
  //    public int applyAsInt(int left, int right){
  //        return -1;
  //    }
  public static void main(final String[] args) {
    int[] x = new int[] {1, 2, 3, 4, 5};
    //        System.out.println(Arrays.stream(x).reduce(0, (a,b) -> a+b));
    System.out.println(Arrays.stream(x).parallel().reduce(0, (a, b) -> a + b));
  }
}
