package com.ssynhtn.hard;

import java.util.Arrays;

public class BuyStockIII {
    private static final int INVALID = Integer.MIN_VALUE;

    public int maxProfitOld(int[] prices) {
        final int n = prices.length;

        // 0: buy, 1: buy sell, 2, bsb, 3, bsbs
        int[][] dp = new int[n+1][4];
        for (int j = 0; j < 4; j++) {
            dp[0][j] = INVALID;
        }

        for (int i = 0; i < n; i++) {
            int l = i + 1;
            dp[l][0] = dp[i][0] == INVALID ? -prices[i] : Math.max(dp[i][0], -prices[i]);
            dp[l][1] = dp[i][0] == INVALID ? dp[i][1] == INVALID ? INVALID : dp[i][1] : Math.max(dp[i][1], prices[i] + dp[i][0]);
            dp[l][2] = dp[i][1] == INVALID ? dp[i][2] == INVALID ? INVALID : dp[i][2] : Math.max(dp[i][2], -prices[i] + dp[i][1]);
            dp[l][3] = dp[i][2] == INVALID ? dp[i][3] == INVALID ? INVALID : dp[i][3] : Math.max(dp[i][3], prices[i] + dp[i][2]);
        }

        int max = 0;
        for (int len = 1; len <= n; len++) {
            if (dp[len][3] != INVALID) {
                max = Math.max(max, dp[len][3]);
            }
            if (dp[len][1] != INVALID) {
                max = Math.max(max, dp[len][1]);
            }
        }

        System.out.println(Arrays.toString(prices));
        for (int[] temp : dp) {
            System.out.println(Arrays.toString(temp));
        }
        return max;
    }

    public int maxProfit(int[] prices) {
        final int n = prices.length;

        // 1
        // 0 or 2
        // 1 or 3
        // 0 or 2 or 4
        int a = -prices[0];
        int b = 0;
        int c = -prices[0];
        int d = 0;
        for (int i = 1; i < n; i++) {
            d = Math.max(d, c + prices[i]);
            c = Math.max(c, b - prices[i]);
            b = Math.max(b, a + prices[i]);
            a = Math.max(a, -prices[i]);
        }

        return d;
    }

    public static void main(String[] args) {
        System.out.println(new BuyStockIII().maxProfit(new int[]{1}));
    }


}
