package com.ssynhtn.medium;

public class NthDigit {

//    k*10^k - (10^k-1)/9 >= n
//
//    k(m+1) - (10^k-1)/9 >= n
//
//            r = f(m) - n
//
//    str[r]

    // 找最小的k
    int findK(int n) {
        int l = 1;
        int r = 10;

        while (l < r) {
            int k = (l + r) / 2;
            int p = power(10, k);
            long c = (long)k * p - (p - 1) / 9;
            if (c == n) return k;
            if (c < n) {
                l = k + 1;
            } else {
                r = k;
            }
        }

        return l;
    }

    private int power(int base, int k) {
        int res = 1;
        for (int i = 0; i < k; i++) {
            res = res * base;
        }
        return res;
    }


    int nthDigit(int n) {
        System.out.println("n " + n);
        int k = findK(n);
        System.out.println("k " + k);
        int p = power(10, k);
        int m = (int) (((long)n + (p-1) / 9 + k - 1) / k - 1);
        System.out.println("m " + m);

        int r = (int)((long)k * (m+1) - (p-1)/9 - n);
        System.out.println("r " + r);
        while (r > 0) {
            m = m / 10;
            r--;
        }

        return m % 10;
    }

    public static void main(String[] args) {
        System.out.println(new NthDigit().nthDigit(1000000000));
    }
}
