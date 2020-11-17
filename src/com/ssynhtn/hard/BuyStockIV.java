package com.ssynhtn.hard;

import javax.crypto.spec.PSource;
import java.util.Collections;
import java.util.PriorityQueue;

public class BuyStockIV {
    public int maxProfit(int k, int[] prices) {

        int i = 0;
        final int n = prices.length;
        int m = 0;
        while (i < n) {
            while (i < n-1 && prices[i] >= prices[i+1] ) {
                i++;
            }
            if (i == n-1) break;
            // i < n-1 && ith < i+1th
            prices[m++] = prices[i];
            int j = i + 1;
            while (j < n-1 && prices[j] < prices[j+1]) {
                j++;
            }

            prices[m++] = prices[j];
            i = j + 1;
        }

        // [0, m) high lows
        k = Math.min(k, m/2);
        int[][] dp = new int[m][k+1];
        return maxProfit(0, m, k, dp, prices);
    }

    // max profit starting at >= i, i even and prices[i] smallest so far
    int maxProfit(int i, int m, int k, int[][] dp, int[] prices) {
        if (k == 0) return 0;
        if (i >= m) return 0;
        if (dp[i][k] > 0) return dp[i][k];

        int max = 0;
        int highest = -1;
        for (int j = i + 1; j < m; j+=2) {
            if (j-1 > i && prices[j-1] <= prices[i]) {
                max = Math.max(max, maxProfit(j - 1, m, k, dp, prices));
                break;
            }

            if (prices[j] > highest) {
                highest = prices[j];
                int sum = prices[j] - prices[i] + maxProfit(j + 1, m, k-1, dp, prices);
                max = Math.max(max, sum);
            }
        }

        dp[i][k] = max;
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new BuyStockIV().maxProfit(2, new int[]{1,2,4,2,5,7,2,4,9,0}));
    }
}
