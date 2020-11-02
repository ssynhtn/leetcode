package com.ssynhtn.medium;

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;

        Arrays.sort(coins);

        int[][] dp = new int[coins.length][amount + 1];
        return recurse(coins, coins.length - 1, amount, dp);
    }

    // i>=0, amount > 0
    private int recurse(int[] coins, int i, int amount, int[][] dp) {
        if (dp[i][amount] != 0) return dp[i][amount];
        int c = coins[i];
        int count = amount / c;
        if (amount % c == 0) {
            dp[i][amount] = count;
            return count;
        }

        if (i == 0) {
            dp[i][amount] = -1;
            return -1;
        }

        int min = Integer.MAX_VALUE;

        for (int j = count; j >= 0; j--) {
            int nextMin = recurse(coins, i - 1, amount - j*c, dp);
            if (nextMin != -1) {
                min = Math.min(min, nextMin + j);
            }
        }


        dp[i][amount] = min == Integer.MAX_VALUE ? -1 : min;
        return dp[i][amount];
    }

    public static void main(String[] args) {

        System.out.println(new CoinChange().coinChange(new int[]{328,122,26,397,252,455,250,252}, 7121));
    }
}
