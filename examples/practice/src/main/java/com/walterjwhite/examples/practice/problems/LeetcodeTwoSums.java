package com.walterjwhite.examples.practice.problems;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LeetcodeTwoSums {

    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int sum = 0;
            final Set<Integer> indices = new HashSet<>();
            for(int i = 0;i < nums.length;i++) {
                indices.add(i);
                sum = nums[i];

                for(int j = nums.length - 1;j >= 0;j--) {
                    if(j == i) {
                        continue;
                    }

                    if(sum + nums[j] > target) {
                        continue;
                    }

                    sum += nums[j];

                    if(sum == target) {
                        return toArray(indices);
                    }

                    if(sum > target) {
                        break;
                    }
                }
            }

            throw new IllegalStateException("Not found");
        }
    }

    private static int[] toArray(final Set<Integer> input) {
        final int[] out = new int[input.size()];
        final Iterator<Integer> inputIterator = input.iterator();
        for(int i = 0;i < input.size();i++){
            out[i] = inputIterator.next();
        }

        return out;
    }
}
