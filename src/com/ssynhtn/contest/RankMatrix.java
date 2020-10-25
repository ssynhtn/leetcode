package com.ssynhtn.contest;

import java.util.Arrays;
import java.util.Comparator;

public class RankMatrix {
    public int[][] matrixRankTransform(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] ranks = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ranks[i][j] = 1;
            }
        }
        Integer[][] ord = new Integer[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ord[i][j] = j;
            }
            int finalI = i;
            Arrays.sort(ord[i], new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(matrix[finalI][o1], matrix[finalI][o2]);
                }
            });
        }
//        printMatrix(ord, "ord");
        Integer[][] ord2 = new Integer[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ord2[i][j] = j;
            }
            int finalI = i;
            Arrays.sort(ord2[i], new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(matrix[o1][finalI], matrix[o2][finalI]);
                }
            });
        }
//        printMatrix(ord2, "ord2");


        while (true) {
            boolean hasMode = false;

            for (int i = 0; i < n; i++) {
                Integer[] ordi = ord[i];
                for (int j = 1; j < m; j++) {
                    int lastIndex = ordi[j - 1];
                    int index = ordi[j];

                    if (matrix[i][index] == matrix[i][lastIndex]) {
                        if (ranks[i][lastIndex] > ranks[i][index]) {
                            ranks[i][index] = ranks[i][lastIndex];
                            hasMode = true;
                        } else if (ranks[i][index] > ranks[i][lastIndex]) {
                            int k = j - 2;
                            while (matrix[i][lastIndex] == matrix[i][index]) {
                                ranks[i][lastIndex] = ranks[i][index];
                                if (k < 0) break;
                                lastIndex = ordi[k--];
                                index = lastIndex;
                            }
                            hasMode = true;
                        }
                    } else { // matrix[i][index] > matrix[i][lastIndex]
                        if (ranks[i][lastIndex] + 1 > ranks[i][index]) {
                            ranks[i][index] = ranks[i][lastIndex] + 1;
                            hasMode = true;
                        }
                    }
                }
            }

//            System.out.println("has mode after row scan " + hasMode);
//            printMatrix(ranks, "ranks so far");

            for (int j = 0; j < m; j++) {
                Integer[] ordj = ord2[j];
                for (int i = 1; i < n; i++) {
                    int lastIndex = ordj[i - 1];
                    int index = ordj[i];

                    if (matrix[index][j] == matrix[lastIndex][j]) {
                        if (ranks[lastIndex][j] > ranks[index][j]) {
                            ranks[index][j] = ranks[lastIndex][j];
                            hasMode = true;
                        } else if (ranks[index][j] > ranks[lastIndex][j]) {
                            int k = i - 2;
                            while (matrix[lastIndex][j] == matrix[index][j]) {
//                                System.out.println("inc at " + lastIndex + "," + j + " from " + ranks[index][j] + " to " + (ranks[lastIndex][j] + 1));
                                ranks[lastIndex][j] = ranks[index][j];
                                if (k < 0) break;
                                lastIndex = ordj[k--];
                                index = lastIndex;
                            }
                            hasMode = true;
                        }
                    } else {
                        if (ranks[lastIndex][j] + 1 > ranks[index][j]) {
//                            System.out.println("inc at " + index + "," + j + " from " + ranks[index][j] + " to " + (ranks[lastIndex][j] + 1));
                            ranks[index][j] = ranks[lastIndex][j] + 1;
                            hasMode = true;
                        }
                    }
                }
            }


//            System.out.println("has mode after col scan " + hasMode);
//            printMatrix(ranks, "ranks so far");

            if (!hasMode) {
                break;
            }
        }

        return ranks;
    }

    public static void main(String[] args) {

        int[][] ranks = new RankMatrix().matrixRankTransform(new int[][]{
                {-23, 20, -49, -30, -39, -28, -5, -14},
                {-19, 4, -33, 2, -47, 28, 43, -6},
                {-47, 36, -49, 6, 17, -8, -21, -30},
                {-27, 44, 27, 10, 21, -8, 3, 14},
                {-19, 12, -25, 34, -27, -48, -37, 14},
                {-47, 40, 23, 46, -39, 48, -41, 18},
                {-27, -4, 7, -10, 9, 36, 43, 2},
                {37, 44, 43, -38, 29, -44, 19, 38}});

        printMatrix(ranks, "ranks");
    }

    static <T> void printMatrix(T[][] mat, String name) {
        System.out.println(name + ": ");
        for (T[] row : mat) {
            System.out.println(Arrays.toString(row));
        }
    }

    static void printMatrix(int[][] mat, String name) {
        System.out.println(name + ": ");
        for (int[] row : mat) {
            System.out.println(Arrays.toString(row));
        }
    }
}
