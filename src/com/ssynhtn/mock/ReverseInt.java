package com.ssynhtn.mock;

import java.util.ArrayList;
import java.util.List;

public class ReverseInt {
    int reverseInt(int x) {
        System.out.println("input " + x);
        if (x == 0) return 0;

        long u = x;
        if (u > 0) {
            long r = reveseLong(u);
            if (r > Integer.MAX_VALUE) {
                return 0;
            }
            return (int) r;
        } else {
            long r = -reveseLong(-u);
            if (r < Integer.MIN_VALUE) {
                return 0;
            }
            return (int) r;

        }
    }

    // u > 0, u <= 2^31
    private long reveseLong(long u) {
        long res = 0;
        while (u > 0) {
            res = res * 10 + (u % 10);
            u = u / 10;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseInt().reverseInt(0));
        System.out.println(new ReverseInt().reverseInt(100));
        System.out.println(new ReverseInt().reverseInt(102));
        System.out.println(new ReverseInt().reverseInt(Integer.MAX_VALUE));
        System.out.println(new ReverseInt().reverseInt(Integer.MAX_VALUE-6));
        System.out.println(new ReverseInt().reverseInt(Integer.MIN_VALUE));
        System.out.println(new ReverseInt().reverseInt(1999999991));
        System.out.println(new ReverseInt().reverseInt(-1999999999));
    }

}
