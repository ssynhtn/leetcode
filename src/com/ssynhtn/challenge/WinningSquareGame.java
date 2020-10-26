package com.ssynhtn.challenge;


import java.util.HashMap;
import java.util.Map;

public class WinningSquareGame {
    public boolean winnerSquareGame(int n) {
        Map<Integer, Boolean> cache = new HashMap<>();
        cache.put(0, false);

        return winner(n, cache);
    }

    private boolean winner(int n, Map<Integer, Boolean> cache) {
        Boolean res = cache.get(n);
        if (res != null) return res;
        int r = (int) Math.sqrt(n);
        for (int i = r; i >= 1; i--) {
            int x = n - i*i;

            if (!winner(x, cache)) { // x losing, so n winning
                cache.put(n, true);
                return true;
            }
        }

        cache.put(n, false);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new WinningSquareGame().winnerSquareGame(0));
        System.out.println(new WinningSquareGame().winnerSquareGame(1));
        System.out.println(new WinningSquareGame().winnerSquareGame(2));
        System.out.println(new WinningSquareGame().winnerSquareGame(4));
        System.out.println(new WinningSquareGame().winnerSquareGame(7));
    }
}
