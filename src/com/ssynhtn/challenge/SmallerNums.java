package com.ssynhtn.challenge;

import java.util.ArrayList;
import java.util.List;

public class SmallerNums {
    public int atMostNGivenDigitSet(String[] digits, int N) {
        int[] ds = new int[digits.length];
        for (int i = 0; i < digits.length; i++) {
            ds[i] = digits[i].charAt(0) - '0';
        }
        
        List<Integer> nds = new ArrayList<>();
        while (N > 0) {
            nds.add(0, N % 10);
            N = N / 10;
        }
        
        int n = ds.length;
        if (n == 1) {
            return countOnes(ds[0], N);
        }
        
        int i = 0;
        int count = 0;
        int m = nds.size();
        while (i < m) {
            int c = nds.get(i);
            int bigger = 0;
            int smaller = 0;
            int same = 0;
            for(int d : ds) {
                if (d > c) {
                    bigger++;
                } else if (d < c) {
                    smaller++;
                } else {
                    same++;
                }
            }

            int p = power(n, m-1-i);
            count += bigger * (p - 1)/(n-1);
            count += smaller * (p * n - 1) / (n-1);

            if (same == 0) break;
            count++;
            i++;
        }
        
        return count;
    }
    
    int countOnes(int d, int n) {
        int sum = 0;
        int count = 0;
        while (true) {
            sum = sum * 10 + d;
            if (sum <= n) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
    
    int power(int x, int c) {
        int s = 1;
        while (c > 0) {
            s = s * x;
            c--;
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(new SmallerNums().atMostNGivenDigitSet(new String[]{"1","4","9"}, 1000000000));
    }
    
}