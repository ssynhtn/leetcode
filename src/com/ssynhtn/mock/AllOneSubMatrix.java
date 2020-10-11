package com.ssynhtn.mock;

import javax.swing.event.MouseInputListener;

public class AllOneSubMatrix {
    public int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) return 0;
        int n = mat.length;
        int m = mat[0].length;

        int count = 0;

        for (int i = 0; i < n; i++) {
            int[] curr = mat[i];
            int[] prev = i == 0 ? null : mat[i - 1];

            for (int j = 0; j < m; j++) {
                int minLen = Integer.MAX_VALUE;
                for (int k = j; k < m && curr[k] != 0 && prev != null && prev[k] > 0; k++) {
                    minLen = Math.min(minLen, prev[k]);
                    count += minLen;
                }
                if (curr[j] != 0) {
                    curr[j] += (prev != null ? prev[j] : 0);
                }
            }

            int j = 0;
            int size;

            while (j < m) {
                size = 0;
                while (j < m && curr[j] != 0) {
                    size++;
                    j++;
                }

                count += (size + 1) * size / 2;
                if (j < m) {
                    j++;
                }
            }
        }


        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 0, 1, },
                {1, 1, 0, },
                {1, 1, 0},
        };

        // 1 + 1 + 6 + 3 + 3 + 1 + 6 + 1 + 2 = 24
        System.out.println(new AllOneSubMatrix().numSubmat(matrix));


    }
}
