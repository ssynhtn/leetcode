package com.ssynhtn.easy;

import java.util.Arrays;

public class ReverseBits {
    public int reverseBits(int n) {
        int[] bits = new int[32];
        int flags = 1;
        for (int i = 0; i < 32; i++) {
            bits[i] = n & flags;
            flags = flags << 1;
        }
//        System.out.println(Arrays.toString(bits));
        int res = 0;
        flags = 1 << 31;
        for (int i = 0; i < 32; i++) {
            if (bits[i] != 0) {
                res = res | flags;
            }
            flags = flags >>> 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int x = 0b00000010100101000001111010011100;
        System.out.println(Integer.toString(x, 2));
        System.out.println(Integer.toString(new ReverseBits().reverseBits(x), 2));
    }
}
