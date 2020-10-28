package com.ssynhtn.mock;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int c = 1;
        int n = digits.length;
        for (int i = n-1; i>= 0; i--) {
            int sum = c + digits[i];
            digits[i] = sum % 10;
            c = sum >= 10 ? 1 : 0;
        }

        if (c == 0) return digits;
        int[] res = new int[n+1];
        res[0] = c;
        System.arraycopy(digits, 0, res, 1, n);
        return res;
    }
}
