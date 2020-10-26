package com.ssynhtn.mock;

import java.util.LinkedList;
import java.util.List;

public class AddArray {
    // A >= 0, K >= 0
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new LinkedList<>();

        int c = 0;
        int i = A.length - 1;
        while (i >= 0 || K > 0) {
            int sum = c + (i >= 0 ? A[i] : 0) + K % 10;
            res.add(0, sum % 10);
            c = sum / 10;
            K = K / 10;
            i--;
        }

        if (c > 0) {
            res.add(0, c);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new AddArray().addToArrayForm(new int[]{9,9,9}, 1));
    }
}
