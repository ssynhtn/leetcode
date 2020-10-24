package com.ssynhtn.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LargestNumber {
    public String largestNumber(int[] nums) {
        List<Integer> data = new ArrayList<>();
        for (int x : nums) {
            data.add(x);
        }
        data.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                if (x.equals(y)) return 0;
                if (x == 0) return 1;
                if (y == 0) return -1;

                long X = x;
                int y2 = y;
                while (y2 > 0) {
                    X = X * 10;
                    y2 = y2 / 10;
                }

                long Y = y;
                int x2 = x;
                while (x2 > 0) {
                    Y = Y * 10;
                    x2 = x2 / 10;
                }

                long diff = X + y - (Y + x);
                return diff > 0 ? -1 : diff < 0 ? 1 : 0;
            }
        });

        if (data.get(0) == 0) return "0";
        StringBuilder sb = new StringBuilder();
        for (Integer x : data) {
            sb.append(x);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LargestNumber().largestNumber(new int[]{3,30,34,5,9}));
    }
}
