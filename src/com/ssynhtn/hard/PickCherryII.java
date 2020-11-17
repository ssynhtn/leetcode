package com.ssynhtn.hard;

import java.util.Arrays;

public class PickCherryII {

//(0, 0) -> (i,j)
//            0<=j<min(m, i+1)
//
//
//            (0,m-1) ->(i,k)
//    max(0, m-1-i)<=k<m
//
//            -2i<=k-(m-1)<=2i
//
//    dp[i,j, i,k] = max[dp[i-1, lastJ, i-1, lastK]] + cherry[i,j]+ cherry[i,k] // single if j == k
//
//    max dp[n-1][j][k]

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][][] dp = new int[n][m][m];
        dp[0][0][m-1] = (m == 1 ? grid[0][0] : grid[0][0] + grid[0][m-1]);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < Math.min(m, i + 1); j++) {
                for (int k = Math.max(0, m-1-i); k < m; k++) {
                    dp[i][j][k] = (j == k ? grid[i][j] : grid[i][j] + grid[i][k]);
                    int max = 0;
                    for (int d = -1; d <= 1; d++) {
                        int lastJ = j + d;
                        if (lastJ < 0 || lastJ >= Math.min(m, i)) continue;
                        for (int e = -1; e <= 1; e++) {
                            int lastK = k + e;
                            if (lastK < Math.max(0, m-i) || lastK >= m) continue;
//                            if (dp[i-1][lastJ][lastK] == 0) continue;
                            max = Math.max(max, dp[i-1][lastJ][lastK]);
                        }
                    }

                    dp[i][j][k] += max;
                }
            }
        }

//        for (int[] r : grid) {
//            System.out.println(Arrays.toString(r));
//        }
//        System.out.println();
//        System.out.println("----");
//        for (int[][] temp : dp) {
//            for (int[] t : temp) {
//                System.out.println(Arrays.toString(t));
//            }
//            System.out.println();
//        }

        int max = 0;
        for (int j = 0; j < Math.min(m, n); j++) {
            for (int k = Math.max(0, m-n); k < m; k++) {
                max = Math.max(dp[n - 1][j][k], max);
            }
        }

//        System.out.println("max " + max);
//        printPath(dp, grid, max);
        return max;
    }

    private void printPath(int[][][] dp, int[][] grid, int max) {
        final int n = grid.length;
        final int m = grid[0].length;

        int i, j, k;
        k = 0;
        boolean found = false;
        for (j = 0; j < Math.min(m, n); j++) {
            for (k = m-1; k >= Math.max(0, m-n); k--) {
                if (dp[n - 1][j][k] == max) {
                    found = true;
                    System.out.println("found " + grid[n-1][j] + ",  " + grid[n-1][k]);
                    break;
                }
            }
            if (found) break;
        }

        if (!found) return;

        i = n-1;
        while (i >= 0) {
            System.out.println("left " + grid[i][j] + ", right " + grid[i][k]);
            if (i == 0) break;

            int add = j == k ? grid[i][j] : grid[i][j] + grid[i][k];
            max -= add;
            found = false;
            for (int d = -1; d <= 1; d++) {
                int lastJ = j + d;
                if (lastJ < 0 || lastJ >= m) continue;
                for (int e = -1; e <= 1; e++) {
                    int lastK = k + e;
                    if (lastK < 0 || lastK >= m) continue;
                    if (max == dp[i-1][lastJ][lastK]) {
                        j = lastJ;
                        k = lastK;
                        found = true;
                        break;
                    }
                }

                if (found) break;
            }

            if (!found) {
                System.out.println("not found");
                break;
            }
            i--;
        }

    }

    public static void main(String[] args) {
        System.out.println(new PickCherryII().cherryPickup(new int[][]{{0,8,7,10,9,10,0,9,6},{8,7,10,8,7,4,9,6,10},{8,1,1,5,1,5,5,1,2},{9,4,10,8,8,1,9,5,0},{4,3,6,10,9,2,4,8,10},{7,3,2,8,3,3,5,9,8},{1,2,6,5,6,2,0,10,0}}));
    }

}
