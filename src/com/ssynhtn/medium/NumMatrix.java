package com.ssynhtn.medium;

public class NumMatrix {
    int[][] matrix;
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        int n = matrix.length;
        if (n == 0) return;
        int m = matrix[0].length;
        if (m == 0) return;

        for (int j = 1; j < m; j++) {
            matrix[0][j] += matrix[0][j-1];
        }
        for (int i = 1; i < n; i++) {
            matrix[i][0] += matrix[i-1][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                matrix[i][j] += matrix[i-1][j] + matrix[i][j-1] - matrix[i-1][j-1];
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(matrix[i][j] + ", ");
//            }
//            System.out.println();
//        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return matrix[row2][col2] - (col1 > 0 ? matrix[row2][col1 - 1] : 0) + (col1 > 0 && row1 > 0 ? matrix[row1 - 1][col1 - 1] : 0) -
                (row1 > 0 ? matrix[row1 - 1][col2] : 0);
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }
}
