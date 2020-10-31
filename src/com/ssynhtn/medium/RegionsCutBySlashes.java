package com.ssynhtn.medium;

public class RegionsCutBySlashes {
    public int regionsBySlashes(String[] grid) {
        int count = 0;
        int n = grid.length;
        boolean[][] up = new boolean[n][n];
        boolean[][] right = new boolean[n][n];
        boolean[][] down = new boolean[n][n];
        boolean[][] left = new boolean[n][n];

        boolean[][][] all = {up, right, down, left};
        for (int d = 0; d < 4; d++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!all[d][i][j]) {
//                        System.out.println("found new area " + direction(d) + ", " + i + "," + j);
                        count++;
                        visit(all, d, i, j, grid);
                    }
                }
            }
        }


        return count;
    }

    private void visit(boolean[][][] all, int d, int i, int j, String[] grid) {
//        System.out.println("visiting " + direction(d) + ", " + i + "," + j);
        all[d][i][j] = true;
        char ch = grid[i].charAt(j);

        boolean updown = d == 0 || d == 2;
        int next = (d + 1) % 4;
        if (!(updown && ch == '/') && !(!updown && ch == '\\') && !all[next][i][j]) {
            visit(all, next, i, j, grid);
        }

        int prev = (d + 3) % 4;
        if (!(updown && ch == '\\') && !(!updown && ch == '/') && !all[prev][i][j]) {
            visit(all, prev, i, j, grid);
        }

        int opposite = (d + 2) % 4;
        int oi, oj;
        if (d == 0) {
            oi = i - 1;
        } else if (d == 2) {
            oi = i + 1;
        } else {
            oi = i;
        }
        if (d == 1) {
            oj = j + 1;
        } else if (d == 3) {
            oj = j - 1;
        } else {
            oj = j;
        }

        if (oi >= 0 && oi < grid.length && oj >= 0 && oj < grid.length && !all[opposite][oi][oj]) {
            visit(all, opposite, oi, oj, grid);
        }

    }

    private static final String[] names = {"up", "right", "down", "left"};
    private String direction(int d) {
        return names[d];
    }


    public static void main(String[] args) {
        System.out.println(new RegionsCutBySlashes().regionsBySlashes(new String[]{
                "\\/\\ "," /\\/"," \\/ ","/ / "
        }));
    }
}
