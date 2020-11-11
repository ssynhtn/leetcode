package com.ssynhtn.medium;

import java.util.Arrays;

public class BeautifulArray {
    public int[] beautifulArray(int N) {
        int[] buffer = new int[N];
        generate(1, 1, N, buffer, 0);
        return buffer;
    }
    
    void generate(int a, int d, int n, int[] buffer, int i) {
        if (n == 1) {
            buffer[i] = a;
            return;
        }
        
        int left = (n+1)/2;
        generate(a, d*2, left, buffer, i);
        generate(a+d, d*2, n-left, buffer, i+left);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BeautifulArray().beautifulArray(10)));
    }
}