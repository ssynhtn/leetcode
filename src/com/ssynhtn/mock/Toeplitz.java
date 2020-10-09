package com.ssynhtn.mock;

public class Toeplitz {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) return true;
        int m = matrix[0].length;
        if (m == 0) return true;

        int s = Math.min(m, n);
        int v = matrix[0][0];
        for (int i = 1; i < s; i++) {
            if (matrix[i][i] != v) return false;
        }

        for (int i = 1; i < n; i++) {
            v = matrix[i][0];
            int index = i + 1;
            int j = 1;
            while (index < n && j < m) {
                if (matrix[index][j] != v) return false;
                index++;
                j++;
            }
        }

        for (int j = 1; j < m; j++) {
            v = matrix[0][j];
            int jdex = j + 1;
            int i = 1;
            while (i < n && jdex < m) {
                if (matrix[i][jdex] != v) {
                    return false;
                }
                i++;
                jdex++;
            }
        }
        return true;

    }
}
