package com.ssynhtn.challenge;


import java.util.HashMap;
import java.util.Map;

public class WinningSquareGame {
    public boolean winnerSquareGame(int n) {
        Boolean[] cache = new Boolean[n + 1];
        cache[0] = false;

        return winnerReverse(n, cache);
    }


    private boolean winnerReverse(int n, Boolean[] cache) {
        Boolean res = cache[n];
        if (res != null) return res;

        for (int i = 1; ; i++) {
            int x = n - i*i;
            if (x < 0) {
                break;
            }

            if (!winnerReverse(x, cache)) { // x losing, so n winning
                cache[n] = true;
                return true;
            }
        }
        cache[n] = false;
        return false;
    }


    public boolean winnerSquareGameArrayCache(int n) {
        Boolean[] cache = new Boolean[n + 1];
        cache[0] = false;

        boolean res = winner(n, cache);
        int count = 0;
        for (int i = 0; i < cache.length; i++) {
            if (cache[i] != null) {
                count++;
            }
        }
        System.out.println("n " + n + ", calculated count " + count);
        return res;
    }

    private boolean winner(int n, Boolean[] cache) {
        Boolean res = cache[n];
        if (res != null) return res;
        int r = (int) Math.sqrt(n);
        for (int i = r; i >= 1; i--) {
            int x = n - i*i;

            if (!winner(x, cache)) { // x losing, so n winning
                cache[n] = true;
                return true;
            }
        }

        cache[n] = false;
        return false;
    }

    public boolean winnerSquareGameMap(int n) {
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
        System.out.println(new WinningSquareGame().winnerSquareGameArrayCache((int) (Math.random() * 100000)));

    }
}
