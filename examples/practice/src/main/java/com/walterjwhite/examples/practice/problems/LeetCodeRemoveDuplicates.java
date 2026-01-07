package com.walterjwhite.examples.practice.problems;

public class LeetCodeRemoveDuplicates {
  public int removeDuplicates(final int[] nums) {
    int ue = 1;
    for (int i = 0; i < (nums.length - 1); i++) {
      if (nums[i + 1] != nums[i]) {
        nums[ue++] = nums[i + 1];
      }
    }

    return ue;
  }
}
