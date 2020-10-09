package com.ssynhtn.medium;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        int count = 0;
        for (int a : A) {
            for (int b : B) {
                int s = a + b;
                map.merge(s, 1, Integer::sum);
            }
        }
        for (int c : C) {
            for (int d : D) {
                int s = -(c + d);
                count += map.getOrDefault(s, 0);
            }

        }

        return count;
    }
}
