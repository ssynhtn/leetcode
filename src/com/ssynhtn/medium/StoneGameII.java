package com.ssynhtn.medium;

import java.util.Arrays;

public class StoneGameII {
//    2,7,9,4,4
//
//    M = 1
//
//    n <= 100
//
//    i = 0, M = 1
//    i = 1, M = 1
//    i = 2, M <= 2
//    i = 3, M = 1, 2
//    i = 4, M <=2
//    i = 5, M <= 3, 2 + 3
//    i = 6, M <= 4: 2 + 4
//
//            2 + 4
//
//    M = max(takes)
//
//[i, n-1]
//
//            0<=i <=n-1
//            1<=M<=n-1
//
//    dp[][]
//
//            i, M
//
//    剩余 n - i个
//            最多拿2M
//
//    take = 1  to min(n-i, 2M)
//    e = i + 1 to min(n, 2M+i)
//
//    dp[i, M] =
//    max {
//        sum(i, i+1) - dp[i+1, max(M, 1)]
//        sum(i, i+2) - dp(i+2, max(M, 2))
//...
//        sum(i, min(n, 2M+i)) - dp(min(n, 2M+i), max(M, min(n, 2M)))
//    }
//
//
//    dp i relies on dp i+
//
//    as for M




//    dp[n+1][n/2]
//
//    dp[i, M]
//    在移除了i个后, M 剩余的东西中最大优势
//
//    j
//1 to min(n-i, 2M)
//
//
//    cum - dp[i+j, nextM]
//
//
//
//    dp[0, 1]
//
//
//    remove 0, -> 1
//    remove 1 -> 1
//
//            1 < x1 < x2 <... < M
//    x1 <= 2
//    x2 <= 2x1
//
//1 + x1 + x2 + ... + M <= len
//
//            设有k个数
//    len >= M(1+1/2+1/4 + ..1/2k-1)
    // => 可以证明 M <= (len+1)/2

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffSum = new int[n+1];
        for (int i = n-1; i>= 0; i--) {
            suffSum[i] = suffSum[i+1] + piles[i];
        }
        int[][] dp = new int[n+1][(n+1)/2+1];

        return maxSum(n, piles, suffSum, dp, 0, 1);

//        for (int[] temp : dp) {
//            System.out.println(Arrays.toString(temp));
//        }

    }

    // after removing i, max advantage
    private int maxSum(int n, int[] piles, int[] suffSum, int[][] dp, final int i, int M) {
        if (i == n) {
            return 0;
        }

        // if (M >= dp[i].length) {
        //     return maxDiff(n, piles, dp, i, dp[i].length - 1);
        // }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }


        if (M * 2 >= n - i) {
            dp[i][M] = suffSum[i];
            return dp[i][M];
        }


        int remove = 0;
        final int removeMax = Math.min(M * 2, n - i);
        int max = Integer.MIN_VALUE;
        int j = i;
        while (remove < removeMax) {
            j++;
            remove++;
            max = Math.max(max, suffSum[i] - maxSum(n, piles, suffSum, dp, j, Math.max(remove, M)));
        }

        dp[i][M] = max;
        return dp[i][M];
    }

    public static void main(String[] args) {
        System.out.println(new StoneGameII().stoneGameII(new int[]{1,2,3,4,5,100}));
    }
}
