package com.ssynhtn.medium;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        char[] chs = word.toCharArray();

        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (exist(board, chs, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    // pos < chs.length
    private boolean exist(char[][] board, char[] chs, int i, int j, int pos) {
        if (board[i][j] != chs[pos]) {
            System.out.println(i + ", " + j + " of " + board[i][j] + " does not match " + pos + " of " + chs[pos]);
            return false;
        }

        System.out.println(i + ", " + j + " of " + board[i][j] + " matches " + pos + " of " + chs[pos]);
        if (pos == chs.length - 1) {
            System.out.println("found!");
            return true;
        }

        for (int x = i - 1; x <= i + 1; x = x + 2) {
            if (x >= 0 && x < board.length) {
                if (board[x][j] != 0) {
                    board[i][j] = 0;
                    if (exist(board, chs, x, j, pos + 1)) {
                        return true;
                    }
                    board[i][j] = chs[pos];
                }
            }
        }

        for (int y = j - 1; y <= j + 1; y = y + 2) {
            if (y >= 0 && y < board[0].length) {
                if (board[i][y] != 0) {
                    board[i][j] = 0;
                    if (exist(board, chs, i, y, pos + 1)) {
                        return true;
                    }
                    board[i][j] = chs[pos];
                }
            }
        }
        return false;

    }

    public static void main(String[] args) {
        char[][] board = {
                {'C','A','A'},
                {'A','A','A'},
                {'B','C','D'}
        };

        System.out.println(new WordSearch().exist(board, "AAB"));
    }
}
