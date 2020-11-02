package com.ssynhtn.medium;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitionII {
    Boolean[][] isPal;

    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];
        isPal = new Boolean[n][n];
        dp[n-1] = 1;
        return compute(dp, s, 0) - 1;
    }

    private int compute(int[] dp, String s, int i) {
        if (dp[i] != 0) return dp[i];

        if (isPal(s, i, s.length() - 1)) {
            dp[i] = 1;
            return 1;
        }

        int min = s.length() - i;

        for (int j = i; j < s.length() - 1; j++) {
            if (isPal(s, i, j)) {
                min = Math.min(min, 1 + compute(dp, s, j + 1));
            }
        }

        dp[i] = min;
        return min;
    }

    private boolean isPal(String s, int i, int j) {
        if (isPal[i][j] != null) return isPal[i][j];
        if (i == j) {
            isPal[i][j] = true;
            return true;
        }
        boolean sameAtEnd = s.charAt(i) == s.charAt(j);;

        if (!sameAtEnd) {
            isPal[i][j] = false;
            return false;
        }

        if (j == i + 1) {
            isPal[i][j] = true;
            return isPal[i][j];
        }

        return isPal[i][j] = isPal(s, i+1,j-1);
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartitionII().minCut("ab"));
    }
}
