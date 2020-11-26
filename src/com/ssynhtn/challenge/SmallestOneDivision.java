package com.ssynhtn.challenge;

public class SmallestOneDivision {
    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0) return -1;
        if (k % 5 == 0) return -1;
        int m = 9;
        while (k % 3 == 0) {
            m = m * 3;
            k = k / 3;
        }

//        System.out.println("k " + k + ", m " + m);
        int a = powerMod1(10, m);
//        System.out.println("m => " + a);
        if (k == 1) return a;
        int b = powerMod1(10, k);
        return a * b / gcd(a, b);
    }

    // (base, k) == 1
    private int powerMod1(int base, int k) {
        int t = 1;
        int r = base % k;
        while (r != 1) {
            r = (r * base) % k;
            t++;
        }
        return t;
    }

    int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }

    public static void main(String[] args) {
        System.out.println(new SmallestOneDivision().smallestRepunitDivByK(21));
    }
}