package com.ssynhtn.mock;

import java.util.ArrayList;
import java.util.List;

public class RotateDigits {
    boolean[] isR = {true, true, true, false, false, true, true, false, true, true};
    boolean[] isRS = {true, true, false, false, false, false, false, false, true, false};
    int[] r = {0, 1, 2, 3, 3, 3, 4, 5, 5, 6, 7};
    int[] rs = {0, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3};

    public int rotatedDigits(int N) {
        List<Integer> digits = new ArrayList<>();
        while (N > 0) {
            digits.add(0, N % 10);
            N = N / 10;
        }
        System.out.println(digits);

        return count(digits, r, isR) - count(digits, rs, isRS);
    }

    private int count(List<Integer> digits, int[] r, boolean[] isR) {
        int count = 0;
        int i;
        for (i = 0; i <= digits.size(); i++) {
            int temp = 1;

            for (int j = i; j < digits.size(); j++) {
                temp *= j == i ? r[digits.get(i)] : r[10];
            }
            count += temp;

            if (i < digits.size() && !isR[digits.get(i)]) break;
        }

        System.out.println(count);
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new RotateDigits().rotatedDigits(2));
    }
}
