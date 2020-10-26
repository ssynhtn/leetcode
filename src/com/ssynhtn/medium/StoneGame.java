package com.ssynhtn.medium;

public class StoneGame {
    public boolean stoneGame(int[] piles) {
        if (quickCheck(piles)) return true;

        int n = piles.length;
        int[][] advantage = new int[n][n];
        for (int i = 0; i < n; i++) {
            advantage[i][i] = piles[i];
        }

        for (int diff = 1; diff < n; diff++) {
            for (int i = 0, j = diff; j < n; i++, j++) {
                advantage[i][j] = Math.max(piles[i] - advantage[i+1][j], piles[j] - advantage[i][j-1]);
            }
        }

        return advantage[0][n-1] > 0;
    }

    private boolean quickCheck(int[] piles) {
        int diff = 0;
        for (int i = 0; i < piles.length; i+=2) {
            diff += piles[i] - piles[i+1];
        }

        return diff != 0;
    }

    public static void main(String[] args) {
        System.out.println(new StoneGame().stoneGame(new int[]{5,3,4,5}));
    }
}
