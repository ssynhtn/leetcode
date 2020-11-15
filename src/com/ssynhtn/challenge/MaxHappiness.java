package com.ssynhtn.challenge;

class MaxHappiness {
    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
//        int N = m * n;
//        if (extrovertsCount >= N) {
//            return 100 * N - 40 * (m + n);
//        }
//
//        if (introvertsCount + extrovertsCount > N) {
//            introvertsCount = N - extrovertsCount;
//        }

        char[][] board = new char[m][n];
        int[][] neiICounts = new int[m][n];
        return getMaxRec(board, neiICounts, m, n, 0, 0, 0, introvertsCount, extrovertsCount);
    }

    int theMax;

    private int getMaxRec(char[][] board, int[][] neiICounts, int m, int n, int i, int j, int sum, int introvertsCount, int extrovertsCount) {
        if (i == m) {
//            System.out.println("sum " + sum);
//            printBoard(board);

            return sum;
        }

        if (extrovertsCount == 0 && introvertsCount == 0) {
//            System.out.println("sum " + sum);
//            printBoard(board);
            return sum;
        }

        // consider top and left
        int neiICount = 0;
        int neiOCount = 0;
        if (i > 0) {
            char top = board[i-1][j];
            if (top == 'i') {
                neiICount++;
            } else if (top == 'o') {
                neiOCount++;
            }
        }
        if (j > 0) {
            char left = board[i][j-1];
            if (left == 'i') {
                neiICount++;
            } else if (left == 'o') {
                neiOCount++;
            }
        }

        int nextI = i;
        int nextJ = j + 1;
        if (nextJ == n) {
            nextI++;
            nextJ = 0;
        }

        neiICounts[i][j] = neiICount;

        int max = 0;
        if (extrovertsCount > 0) {
            board[i][j] = 'o';
            int add = 40 + 40 * neiOCount - 10 * neiICount;
            max = Math.max(max, getMaxRec(board, neiICounts, m, n, nextI,nextJ, sum + add, introvertsCount, extrovertsCount-1));
            board[i][j] = 0;
        }

        max = Math.max(max, getMaxRec(board, neiICounts, m, n, nextI, nextJ, sum, introvertsCount, extrovertsCount));

        if (introvertsCount > 0 && neiICount < 2 && (i == 0 || board[i-1][j] != 'i' || neiICounts[i-1][j] < 1) &&
                (j == 0 || board[i][j-1] != 'i' || neiICounts[i][j-1] < 1)) {
            if (i > 0) {
                neiICounts[i-1][j]++;
            }
            if (j > 0) {
                neiICounts[i][j-1]++;
            }
            board[i][j] = 'i';
            int add = 120 - 10 * neiOCount - 60 * neiICount;
            max = Math.max(max, getMaxRec(board, neiICounts, m, n, nextI, nextJ, sum + add, introvertsCount - 1, extrovertsCount));
            board[i][j] = 0;

            if (i > 0) {
                neiICounts[i-1][j]--;
            }
            if (j > 0) {
                neiICounts[i][j-1]--;
            }
        }

        neiICounts[i][j] = 0;

        return max;
    }

    private void printBoard(char[][] board) {
        for (char[] b : board) {
            for (char ch : b) {
                if (ch == 0) {
                    System.out.print('-');
                } else {
                    System.out.print(ch);
                }

            }
            System.out.println();
        }
        System.out.println();
    }
    private void printBoard(int[][] board) {
        for (int[] b : board) {
            for (int ch : b) {
                if (ch == 0) {
                    System.out.print('-');
                } else {
                    System.out.print(ch);
                }

            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(new MaxHappiness().getMaxGridHappiness(5, 5, 3, 6));
    }
}