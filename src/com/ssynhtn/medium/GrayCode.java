package com.ssynhtn.medium;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> codes = new ArrayList<>();
        codes.add(0);

        int size = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = size - 1; j >= 0; j--) {
                codes.add(codes.get(j) | size);
            }

            size = size << 1;
        }

        return codes;
    }

    public static void main(String[] args) {
        System.out.println(new GrayCode().grayCode(2));
    }
}
