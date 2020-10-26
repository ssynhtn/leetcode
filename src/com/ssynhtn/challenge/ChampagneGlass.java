package com.ssynhtn.challenge;

public class ChampagneGlass {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] glasses = new double[query_row + 1][query_glass + 1];

        glasses[0][0] = poured;
        for (int i = 1; i <= query_row; i++) {
            for (int j = Math.max(i-query_row+query_glass, 0); j <= Math.min(query_glass, i); j++) {
                double topLeft = j == 0 ? 0 : Math.max((glasses[i - 1][j - 1] - 1) / 2, 0);
                double topRight = j == i ? 0 : Math.max((glasses[i - 1][j] - 1) / 2, 0);
                glasses[i][j] = topLeft + topRight;
            }
        }

        return Math.min(glasses[query_row][query_glass], 1);
    }

    public static void main(String[] args) {
        System.out.println(new ChampagneGlass().champagneTower(100000009, 33, 17));
    }
}
