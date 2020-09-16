package com.ssynhtn.medium;

public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;

        int i1 = 0;
        int j1 = 0;
        int i2 = matrix.length - 1;
        int j2 = matrix[0].length - 1;
        int n = matrix[0].length;

        while (i1 < i2 || j1 <= j2) {
            int i, j;

            i = (i1 + i2) / 2;
            if (((i2 - i1) & 1) == 1) {
                j = (j1 + j2 + n) / 2;
                if (j >= n) {
                    j = j - n;
                    i++;
                }
            } else {
                j = (j1 + j2) / 2;
            }

//            System.out.println("i " + i + ", j " + j + ", num " + matrix[i][j]);

            if (matrix[i][j] == target) return true;
            if (matrix[i][j] > target) {
                if (i == 0 && j == 0) return false;
                i2 = i;
                j2 = j - 1;
                if (j2 < 0) {
                    i2--;
                    j2 = n - 1;
                }

                if (i2 < i1 || i2 == i1 && j2 < j1) return false;

            } else {
                if (i == matrix.length - 1 && j == matrix[0].length - 1) {
                    return false;
                }

                i1 = i;
                j1 = j + 1;
                if (j1 == n) {
                    i1++;
                    j1 = 0;
                }

                if (i1 > i2 || i1 == i2 && j1 > j2) return false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,50},

        };

        System.out.println(new SearchMatrix().searchMatrix(matrix, 3));
    }
}
