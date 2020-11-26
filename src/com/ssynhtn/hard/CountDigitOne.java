package com.ssynhtn.hard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CountDigitOne {
    int[] power10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
    public int countDigitOne2(int n) {
        int m = n / 10;
        int t = 0;
        int p10 = 1;
        int d = n % 10;

        int count = 0;
        while (t < n) {
            count += m * p10;
            if (d > 1) {
                count += p10;
            } else if (d == 1) {
                count += t + 1;
            }
            t = d * p10 + t;
            d = m % 10;
            m = m / 10;
            p10 *= 10;
        }

        return count;

    }
    public int countDigitOne(int N) {
        if (N <= 0) return 0;
//        System.out.println(N);

        // n >= 1
// n = abcdefg, k = 7
// m = bcdefg


// 01,...a


// 0,2,3,...a-1
// 10^(k-2)*(k-1)*(a-1 if a > 1 else 1)


// a = 1: m+1 + rec(m)

// a > 1
// 10^(k-2)*(k-1) + 10^(k-1) + rec(m)

        int res = 0;

        ArrayDeque<Integer> digits = new ArrayDeque<>();
        while (N > 0) {
            digits.addFirst(N % 10);
            N = N / 10;
        }
        final int n = digits.size();
        int[] ds = new int[n];
        int i = 0;
        while (!digits.isEmpty()) {
            ds[i++] = digits.removeFirst();
        }
        int[] ms = new int[n];
        ms[n-1] = ds[n-1];
        for (i = n-2; i >= 0; i--) {
            ms[i] = ms[i+1] + ds[i] * power10[n-i-1];
        }

        System.out.println(Arrays.toString(ds));
        System.out.println(Arrays.toString(ms));


        i = 0;
        int k = n;

        while (i < n-1) {
            int a = ds[i];
            if (a == 0) {
                i++;
                k--;
                continue;
            }

            res += power10[k - 2] * (k - 1) * (a == 1 ? 1 : a - 1);

            if (a == 1) {
                res += ms[i+1] + 1;
            } else {
                res += power10[k-2]*(k-1) + power10[k-1];
            }
            i++;
            k--;
        }
        // i == n-1
        if (ds[i] >= 1) {
            res++;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new CountDigitOne().countDigitOne2(824883294));
        System.out.println(new CountDigitOne().countDigitOne(824883294));
    }


}