package com.ssynhtn.hard;

import java.util.HashMap;
import java.util.Map;

class NumWaysWalk {
    private static final int N = 1000000007;
    public int numWays(int steps, int arrLen) {
        if (arrLen == 1) return 1;
        arrLen = Math.min(1 + steps, arrLen);

        int[][] cache = new int[steps + 1][arrLen];

        return count(0, steps, arrLen, cache);
    }

    // n >= 2
    private int count(int i, int s, int n, int[][] cache) {
        if (s < i) return 0;
        if (s == i) return 1;
        // s > i
        int temp = cache[s][i];
        if (temp != 0) return temp;

        int count;
        if (i == 0) {
            count = count(i, s-1, n, cache) + count(i + 1, s-1, n, cache);
        } else if (i == n - 1) {
            count = count(i, s-1, n, cache) + count(i - 1, s-1, n, cache);
        } else {
            count = count(i, s - 1, n, cache) + count(i - 1, s - 1, n, cache);
            count = count % N;
            count += count(i + 1, s - 1, n, cache);
        }
        count = count % N;
        cache[s][i] = count;
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new NumWaysWalk().numWays(4, 3));
    }
}