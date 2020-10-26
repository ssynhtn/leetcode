package com.ssynhtn.mock;

import java.util.Arrays;

public class MatrixBlockSum {
    public int[][] matrixBlockSumPrefixSum(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] sum = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sum[i][j] = sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1] + mat[i-1][j-1];
            }
        }

        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int l = Math.max(i-k, 0), r = Math.min(i+k+1, n);
                int t = Math.max(j - k, 0), b = Math.min(j + k + 1, m);

                res[i][j] = sum[r][b] + sum[l][t] - sum[l][b] - sum[r][t];
            }
        }
        return res;
    }
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int n = mat.length;
        int m = mat[0].length;

        int[][] res = new int[n][m];

        int[] sum = new int[Math.max(m, n)];
        final int c = Math.min(K+1, m);
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j = 0; j < c; j++) {
                s += mat[i][j];
            }
            sum[i] = s;
        }

//        System.out.println("sum " + Arrays.toString(sum));

        final int w = Math.min(K+1, n);
        int s = 0;
        for (int i = 0; i < w; i++) {
            s += sum[i];
        }
        res[0][0] = s;

        int start = 0;
        int end = w;
        int index = 1;
        while (index < n) {
            if (index - start > K) {
//                System.out.println("height " + end + " - " + start + " reached " + (2*K+ 1));
                s -= sum[start++];
            }
            if (end < n) {
                s += sum[end++];
            }

            res[index++][0] = s;
        }

        for (int j = 0; j < m; j++) {
            int temp = 0;
            for (int i = 0; i < w; i++) {
                temp += mat[i][j];
            }
            sum[j] = temp;
        }
//        System.out.println("sum " + Arrays.toString(sum));

        start = 0;
        end = c;
        index = 1;
        s = res[0][0];
        while (index < m) {
            if (index - start > K) {
//                System.out.println("width " + end + " - " + start + " reached " + (2*K+ 1));
                s -= sum[start++];
            }
            if (end < m) {
                s += sum[end++];
            }

            res[0][index++] = s;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                res[i][j] += res[i-1][j] + res[i][j-1] - res[i-1][j-1];
                int xMax = i + K;
                int yMax = j + K;
                int xMin = i - K - 1;
                int yMin = j - K - 1;

                if (xMax < n && yMax < m) {
                    res[i][j] += mat[xMax][yMax];
                }
                if (xMin >= 0 && yMin >= 0) {
                    res[i][j] += mat[xMin][yMin];
                }
                if (xMax < n && yMin >= 0) {
                    res[i][j] -= mat[xMax][yMin];
                }
                if (xMin >= 0 && yMax < m) {
                    res[i][j] -= mat[xMin][yMax];
                }
            }
        }

        return res;

    }

    public static void main(String[] args) {
//        [[67,64,78],[99,98,38],[82,46,46],[6,52,55],[55,99,45]]
//        3
        int[][] mat = {
                {67,64,78},{99,98,38},{82,46,46},{6,52,55},{55,99,45}
        };

        for (int[] r : mat) {
            System.out.println(Arrays.toString(r));
        }

        int[][] res = new MatrixBlockSum().matrixBlockSumPrefixSum(mat, 3);
        for (int[] r : res) {
            System.out.println(Arrays.toString(r));
        }
    }
}
