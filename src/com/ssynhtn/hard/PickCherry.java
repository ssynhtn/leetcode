package com.ssynhtn.hard;

import java.util.Arrays;

public class PickCherry {
    public int cherryPickup(int[][] grid) {
//         dp[i,j,k] from (0,0) to [i,j], and (0,0) to [k,l = i+j-k], 最多能多少

// 0<=k<n
// 0<=i+j-k<n

// special_add([i-1, j, k-1, l] + cherry[i,j] + cherry[k, j])
// special_add([i-1, j, k, l-1], . .)
// special_add([i, j-1, k-1, l])
// special_add([i, j-1, k, l-1)

// (i,j) == (0, 0)  (k, j) == (0, 0)

// dp(0,0,0) = cherry[0,0]
        
        
        int n = grid.length;
        int m = grid[0].length;
        
        int[][][] dp = new int[n][m][n];
        
        dp[0][0][0] = grid[0][0];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) continue;
                int lower = Math.max(0, i + j - m + 1);
                int upper = Math.min(n, i + j + 1);

                for (int k = lower; k < upper; k++) {
                    int max = -1;
                    int l = i + j - k;
                    if (grid[i][j] == -1 || grid[k][l] == -1) {
                        dp[i][j][k] = -1;
                        continue;
                    }
                    
                    int newCell = (i == k && j == l) ? grid[i][j] : grid[i][j] + grid[k][l];
                    
                    for (int d = 0; d <= 1; d++) {
                        int lastI = i - d;
                        int lastJ = j - 1+d;
                        if(lastI < 0 || lastJ < 0) continue;
                        for (int e = 0; e <= 1; e++) {
                            
                            int lastK = k - e;
                            int lastL = l -1+e;
                            
                            if (lastK < 0 || lastL < 0) continue;
                            
                            if (dp[lastI][lastJ][lastK] != -1) {
                                max = Math.max(max, dp[lastI][lastJ][lastK] + newCell);
                            }
                        }
                    }
                    
                    dp[i][j][k] = max;
                }
            }
        }

        for (int[][] t : dp) {
            for (int[] s : t) {
                System.out.println(Arrays.toString(s));
            }
        }
        System.out.println(dp[0][0][0]);
        return Math.max(0, dp[n-1][m-1][n-1]);
    }

    int memo(int[][] grid, int[][][] dp, int i, int j, int k) {
        if (dp[i][j][k] != 0) return dp[i][j][k];



        int l = i + j - k;
        if (grid[i][j] == -1 || grid[k][l] == -1) {
            dp[i][j][k] = 1;
            return 1;
        }

        int max = 1;
        int newCell = (i == k && j == l) ? grid[i][j] : grid[i][j] + grid[k][l];

        for (int d = 0; d <= 1; d++) {
            int lastI = i - d;
            int lastJ = j - 1+d;
            if(lastI < 0 || lastJ < 0) continue;
            for (int e = 0; e <= 1; e++) {

                int lastK = k - e;
                int lastL = l -1+e;

                if (lastK < 0 || lastL < 0) continue;

                if (memo(grid, dp, lastI, lastJ, lastK) != 1) {
                    max = Math.max(max, dp[lastI][lastJ][lastK] + newCell);
                }
            }
        }

        dp[i][j][k] = max;

        return max;
    }

    public int cherryPickupMemo(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][][] dp = new int[n][m][n];

        // +2, 0 as null
        dp[0][0][0] = grid[0][0] + 2;

        int res = memo(grid, dp, n-1, m-1, n-1) - 2;
        return res == -1 ? 0 : res;

    }

    public static void main(String[] args) {
        System.out.println(new PickCherry().cherryPickupMemo(new int[][]{{0,1,-1},{1,0,-1},{1,1,1}}));
    }
}