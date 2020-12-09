package com.ssynhtn.hard;

import java.util.Arrays;

class NumDISeq {
    static final int M = 1000000007;
    public int numPermsDISequence(String S) {
        char[] chs = S.toCharArray();
        int n = chs.length;
        int[][] cache = new int[n+1][n+1];


        for (int k = 0; k <= n; k++) {
            cache[n][k] = 1;
        }
        for (int i = n-1; i >= 0; i--) {
            for (int k = 0; k <= n-i; k++) {
                int sum = 0;
                if (chs[i] == 'D') {
                    for (int j = 0; j < k; j++) {
                        sum += cache[i+1][j];
                        sum = sum % M;
                    }
                } else {
                    for (int j = k; j < n - i; j++) {
                        sum += cache[i+1][j];
                        sum = sum % M;
                    }
                }
                cache[i][k] = sum;
            }
        }

        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += cache[0][i];
            sum = sum % M;
        }

//        for (int[] row : cache) {
//            System.out.println(Arrays.toString(row));
//        }

        return sum;
    }

    // 0 <= k <= n-i
    private int compute(char[] chs, int[][] cache, int i, int k) {
        if (cache[i][k] > 0) {
            return cache[i][k];
        }

        if (i == chs.length) {
            cache[i][k] = 1;
            return 1;
        }

        int sum = 0;
        if (chs[i] == 'D') {
            for (int j = 0; j < k; j++) {
                sum += compute(chs, cache, i + 1, j);
                sum = sum % M;
            }
        } else {
            for (int j = k; j < chs.length - i; j++) {
                sum += compute(chs, cache, i + 1, j);
                sum = sum % M;
            }
        }

        cache[i][k] = sum;
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new NumDISeq().numPermsDISequence("DID"));
    }
}