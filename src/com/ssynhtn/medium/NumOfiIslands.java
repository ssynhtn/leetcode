package com.ssynhtn.medium;

public class NumOfiIslands {
    public int numIslands(char[][] grid) {
        int count = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0') continue;

                count++;
                visit(grid, i, j, n, m);
//                printVisited(grid);


            }
        }


        return count;
    }

    private void printVisited(boolean[][] visited) {
        for (boolean[] row : visited) {
            for (boolean x : row) {
                System.out.print(x ? "1" : "0");
                System.out.print(",");
            }
            System.out.println();
        }
        System.out.println();
    }

    int[] idir = {-1, 1, 0, 0};
    int[] jdir = {0, 0, -1, 1};
    private void visit(char[][] grid, int i, int j, int n, int m) {
        grid[i][j] = '0';

        for (int k = 0; k < 4; k++) {
            int x = i + idir[k];
            int y = j + jdir[k];

            if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == '1' && grid[x][y] == '1') {
                visit(grid, x, y, n, m);
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(new NumOfiIslands().numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}}));
    }
}
