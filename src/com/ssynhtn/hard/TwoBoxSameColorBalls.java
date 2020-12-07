package com.ssynhtn.hard;

public class TwoBoxSameColorBalls {
    long[][] cache = new long[48][24];
    public double getProbability(int[] balls) {
        int n2 = 0;
        for (int x : balls) {
            n2 += x;
        }

        int n = n2 / 2;
        long base = choose(n2, n);

        int[] counts = new int[balls.length];
        long sum = collect(counts, 0, 0, 0, 0, balls, n);


//        System.out.println("sum " + sum + ", base " + base);
        return sum * 1.0 / base;
    }

    private long collect(int[] counts, int prefixSum, int i, int zero, int full, int[] balls, int n) {
        if (i == counts.length) {
            if (zero == full && prefixSum == n) {
                return compute(counts, balls);
            } else {
                return 0;
            }
        }

        if (Math.abs(full - zero) > balls.length - i) return 0;
        if (prefixSum > n) return 0;

        long res = collect(counts, prefixSum, i + 1, zero + 1, full, balls, n);
        counts[i] = balls[i];
        res += collect(counts, prefixSum + balls[i], i + 1, zero, full + 1, balls, n);
        for (int x = 1; x < balls[i]; x++) {
            counts[i] = x;
            res += collect(counts, prefixSum + x, i + 1, zero, full, balls, n);
        }
        counts[i] = 0;

        return res;
    }

    private long compute(int[] counts, int[] balls) {
        long res = 1;
        for (int i = 0; i < counts.length; i++) {
            res *= choose(balls[i], counts[i]);
        }
        return res;
    }

    long choose(int n, int k) {
        if (k * 2 > n) {
            k = n - k;
        }

        if (cache[n][k] != 0) return cache[n][k];

        long prd = 1;
        int i = 1;
        int j = n - k + 1;
        for (; i <= k; i++, j++) {
            prd = prd * j / i;
        }

        cache[n][k] = prd;
        return prd;
    }

    public static void main(String[] args) {
        System.out.println(new TwoBoxSameColorBalls().getProbability(new int[]{6,6,6,6,6,6}));
    }
}
