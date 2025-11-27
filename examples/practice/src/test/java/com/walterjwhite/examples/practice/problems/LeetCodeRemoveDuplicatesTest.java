package com.walterjwhite.examples.practice.problems;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

class LeetCodeRemoveDuplicatesTest {
    private final LeetCodeRemoveDuplicates leetCodeRemoveDuplicates = new LeetCodeRemoveDuplicates();

    @Test
    void tests() {
        final int[] input = new int[]{0,0,1,1,1,2,2,3,4};
        Assertions.assertThat(leetCodeRemoveDuplicates.removeDuplicates(input)).isEqualTo(5);

        Assertions.assertThat(input[0]).isEqualTo(0);
        Assertions.assertThat(input[1]).isEqualTo(1);
        Assertions.assertThat(input[2]).isEqualTo(2);
        Assertions.assertThat(input[3]).isEqualTo(3);
        Assertions.assertThat(input[4]).isEqualTo(4);
    }
}