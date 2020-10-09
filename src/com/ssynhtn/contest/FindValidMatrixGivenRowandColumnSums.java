package com.ssynhtn.contest;

import com.ssynhtn.medium.FindDuplicates;
import com.sun.xml.internal.fastinfoset.tools.XML_SAX_StAX_FI;

import java.util.Arrays;

public class FindValidMatrixGivenRowandColumnSums {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int n = rowSum.length;
        int m = colSum.length;
        int[][] matrix = new int[n][m];

        for (int i = 0; i < n - 1; i++) {
            matrix[i][m - 1] = rowSum[i];
        }

        long lastRowSum = 0;
        for (int j = 0; j < m - 1; j++) {
            matrix[n - 1][j] = colSum[j];
            lastRowSum += colSum[j];
        }

        long last = rowSum[n - 1] - lastRowSum;

        int i = 0;
        int j = 0;
        while (last < 0) {
            while (matrix[i][m - 1] == 0) {
                i++;
            }
            while (matrix[n - 1][j] == 0) {
                j++;
            }

            int min = Math.min(matrix[i][m - 1], matrix[n - 1][j]);
            matrix[i][j] += min;
            matrix[i][m - 1] -= min;
            matrix[n - 1][j] -= min;
            last += min;
        }

        matrix[n-1][m-1] = (int) last;

//        test(rowSum, colSum, matrix);
        return matrix;
    }

    private void test(int[] rowSum, int[] colSum, int[][] matrix) {
        int n = rowSum.length;
        int m = colSum.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += matrix[i][j];
            }
            if (sum != rowSum[i]) {
                System.out.println("bad at " + i + " th row");
                return;
            }
        }
        for (int j = 0; j < m; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += matrix[i][j];
            }
            if (sum != colSum[j]) {
                System.out.println("bad at " + j + " th col");
                return;
            }
        }

        System.out.println("good");
    }

    public static void main(String[] args) {
        int size = 500;
        int[] rowSum = new int[size];
        int[] colSum = new int[size];
        for (int i = 0; i < size; i++) {
            rowSum[i] = 100000000;
            colSum[i] = 100000000;
        }
        int[][] matrix = new FindValidMatrixGivenRowandColumnSums().restoreMatrix(rowSum, colSum);
//        for (int[] row : matrix) {
//            System.out.println(Arrays.toString(row));
//        }
    }
}
