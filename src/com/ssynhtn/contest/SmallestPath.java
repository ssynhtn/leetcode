package com.ssynhtn.contest;

import org.omg.PortableInterceptor.INACTIVE;

import javax.swing.*;

public class SmallestPath {
//    target (n, m)
//
//    C(n,n) = 1
//    C(n+1, n)
//    C(n+2, n)
//
//
//    C(n+k, n)
//
//    find smallest k that C(n+k, n) >= target
//
//    k = 0, H*m, target (n, 0)
//    k= 1, H*m-1, target (n, 1)
//    k = m-1, H, target(n, m-1)
//    k = m, V, target (n-1, m)
//
//
//    target: (m
//
//
//    dp [n+1, m+1]
//    dp[i,j] = choose(i+j,iorj)

    public String kthSmallestPath(int[] destination, int target) {
        StringBuilder sb = new StringBuilder();
        int n = destination[0];
        int m = destination[1];

        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 1;
        }

//        k = 0, H*m, target (n, 0), rank
//        k= 1, H*m-1, target (n, 1)
//        k = m-1, H, target(n, m-1)
//        k = m, V, target (n-1, m) rank - C(n+m-1, n)
//
//        n > 0 || m > 0

        while (n > 0 || m > 0) {
            int k = 0;
            while (compute(n, k, dp) < target) {
                k++;
            }

            if (k < m) {
                for (int i = 0; i < m-k; i++) {
                    sb.append("H");
                }
                m = k;
            } else {
                sb.append("V");
                target = target - (m > 0 ? dp[n][m-1]:0);
                n = n -1;
            }
        }


        return sb.toString();
    }


    private int compute(int n, int k, int[][] dp) {
        if (dp[n][k] != 0) return dp[n][k];
        // n + k, k => n+k-1, k-1, n+k-1, k
        dp[n][k] = compute(n, k-1, dp) + compute(n-1, k, dp);
        return dp[n][k];
    }

    public static void main(String[] args) {
        System.out.println(new SmallestPath().kthSmallestPath(new int[]{2,3}, 2));
    }
}
