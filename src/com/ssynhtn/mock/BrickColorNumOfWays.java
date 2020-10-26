package com.ssynhtn.mock;


public class BrickColorNumOfWays {
    public int numOfWaysTwo(int n) {
        int mod = 1000000007;
        int r = 6;
        int s = 6;

        for (int i = 2; i <= n; i++) {
            r += s;
            if (r > mod) {
                r -= mod;
            }
            r *= 2;
            if (r > mod) {
                r -= mod;
            }
            s += r;
            if (s > mod) {
                s -= mod;
            }
        }

        r += s;
        if (r > mod) {
            r -= mod;
        }
        return r;
    }

    public int numOfWays(int n) {
        int mod = 1000000007;
        int r = 6;
        int s = 6;

        for (int i = 2; i <= n; i++) {
            r = 2 * ((r + s) % mod) % mod;
            s = (s + r) % mod;
        }

        return (r + s) % mod;
    }

    public static void main(String[] args) {
        System.out.println(new BrickColorNumOfWays().numOfWaysTwo(5000));
    }
}
