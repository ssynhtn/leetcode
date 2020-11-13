package com.ssynhtn.medium;

public class RangeBitwiseAnd {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == n) return m;
        int flag = 1 << 31;
        while ((m & flag) == (n & flag)) {
            flag = flag >>> 1;
        }

        return m & -(flag << 1);

    }

    public static void main(String[] args) {
        System.out.println(new RangeBitwiseAnd().rangeBitwiseAnd(5, 7));
    }
}
