package com.ssynhtn.contest;

import java.util.Arrays;
import java.util.Comparator;

class MaxHeight {
    public int maxHeight(int[][] cuboids) {
        for (int[] cub : cuboids) {
            Arrays.sort(cub);
        }

        Arrays.sort(cuboids, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[2] < b[2]) return -1;
                if (a[2] > b[2]) return 1;
                if (a[1] < b[1]) return -1;
                if (a[1] > b[1]) return 1;
                return Integer.compare(a[0], b[0]);
            }
        });

        int n = cuboids.length;
        int[] dp = new int[n];

        dp[0] = cuboids[0][2];
        for (int i = 1; i < n; i++) {
            int max = cuboids[i][2];
            for (int j = 0; j < i; j++) {
                if (strictlySmaller(cuboids[j], cuboids[i])) {
                    max = Math.max(max, dp[j] + cuboids[i][2]);
                }
            }
            dp[i] = max;
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private boolean strictlySmaller(int[] a, int[] b) {
        return a[0] <= b[0] && a[1] <= b[1] && a[2] <= b[2];
    }

    public static void main(String[] args) {
        System.out.println(new MaxHeight().maxHeight(new int[][]{
                {38,25,45 }, {76,35,3 }
                }
        ));
    }
}