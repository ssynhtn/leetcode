package com.ssynhtn.contest;

import java.util.Arrays;

public class NWays {
    private static final int MOD = 1000000007;
    public int numWays(String[] words, String target) {
        int n = words[0].length();
        int k = target.length();

        if (k > n) return 0;

        int[][] f = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (String s : words) {
                char ch = s.charAt(i);
                f[i][ch - 'a']++;
            }
        }

        long[][] dp = new long[k+1][n+1];
        for (long[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        for (int j = 0; j <= n; j++) {
            dp[k][j] = 1;
        }

        compute(f, dp, 0, 0, target, n, k);


//        for (int i = k-1; i >= 0; i--) {
//            int c = target.charAt(i) - 'a';
//            int limit = n - (k-i);
//            for (int j = limit; j >= 0; j--) {
//                dp[i][j] = (dp[i][j] + f[j][c] * dp[i+1][j + 1] + dp[i][j+1]) % MOD;
//            }
//        }

        return (int) dp[0][0];
    }

    private long compute(int[][] f, long[][] dp, int i, int j, String target, int n, int k) {
        if (dp[i][j] != -1) return dp[i][j];
        int c = target.charAt(i) - 'a';
        long s = 0;
        if (f[j][c] > 0) {
            s = f[j][c] * compute(f, dp, i+1, j + 1, target, n, k);
        }
        if (n - j - 1 >= k - i) {
            s += compute(f, dp, i, j + 1, target, n, k);
        }
        dp[i][j] = s % MOD;
        return dp[i][j];
    }

    public static void main(String[] args) {
        System.out.println(new NWays().numWays(new String[]{"acca","bbbb","caca"}, "aba"));
    }
//    每个word长度为n, target 长度为k
//
//
//    f[n][26]
//    f[i,c] 第i个index上字母c的个数
//
//
//    dp[k+1][n+1]
//
//    dpi,j: target从i开始的substring, words从>=j开始的
//
//    k-i <= n-j or else 0
//
//    c = target[i]
//    dp[i,j] = f[j][c] * dp[i+1, j+1] + f[j+1][c] * dp[i+1, j+2] + ...
//
//
//    i = k -> 1
//    i = k-1 to 0
//    j = 0 to n-(k-i)

}
