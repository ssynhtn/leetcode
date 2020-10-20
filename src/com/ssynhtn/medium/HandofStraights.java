package com.ssynhtn.medium;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class HandofStraights {

    public boolean isNStraightHandSortedSet(int[] hand, int W) {
        int n = hand.length;
        if (n % W != 0) return false;
        TreeMap<Integer, Integer> counts = new TreeMap<>();
        for (int x : hand) {
            counts.merge(x, 1, Integer::sum);
        }

        while (!counts.isEmpty()) {
            Map.Entry<Integer, Integer> first = counts.pollFirstEntry();
            int min = first.getKey();
            int d = first.getValue();

            for (int i = min + 1; i < min + W; i++) {
                Integer c = counts.get(i);
                if (c == null) return false;
                if (c < d) return false;
                if (c == d) {
                    counts.remove(i);
                } else {
                    counts.put(i, c - d);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new HandofStraights().isNStraightHandSortedSet(new int[]{1,2,3,6,2,3,4,7,8}, 3));
    }
}
