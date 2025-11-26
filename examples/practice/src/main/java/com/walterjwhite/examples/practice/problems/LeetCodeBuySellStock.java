package com.walterjwhite.examples.practice.problems;

public class LeetCodeBuySellStock {
    public int maxProfit(final int[] prices) {
        int profit = 0;
        boolean holding = false;
        for(int i = 0;i < (prices.length - 1);i++) {
            if(!holding) {
                if (prices[i + 1] < prices[i]) {
                    profit -= prices[i + 1];
                    holding = true;
                } else {
                    profit += prices[i+1] - prices[i];
                }
            } else {
                if (prices[i + 1] > prices[i]) {
                    profit += prices[i + 1];
                    holding = false;
                }
            }
        }

        return profit;
    }

    public static void main(final String[] args) {
        int i = 0;
        i -= 1;
        i += 4;

        System.out.println(("i:" + i));
    }
}
