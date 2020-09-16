package com.ssynhtn.hard;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        // dist[i][j]: word1的i个char -> word2的j个char
        int l1 = word1.length();
        int l2 = word2.length();

        int[][] dist = new int[l1+ 1][l2 + 1];
        dist[0][0] = 0;
        for (int j = 1; j <= l2; j++) {
            dist[0][j] = j;
        }
        for (int i = 1; i <= l1; i++) {
            dist[i][0] = i;
        }

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (word1.charAt(l1 - i) == word2.charAt(l2 - j)) {
                    dist[i][j] = dist[i-1][j-1];
                } else {
                    dist[i][j] = 1 + Math.min(Math.min(dist[i][j-1], dist[i-1][j]), dist[i-1][j-1]);
                }
            }
        }

        return dist[l1][l2];
    }

    public static void main(String[] args) {
        System.out.println(new EditDistance().minDistance("b", ""));
    }
}
