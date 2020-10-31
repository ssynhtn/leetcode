package com.ssynhtn.easy;

import java.util.*;
import java.util.function.BiFunction;

public class LHS {
    public int findLHSTreeMap(int[] nums) {
        TreeMap<Integer, Integer> counts = new TreeMap<>();
        for (int x : nums) {
            counts.merge(x, 1, new BiFunction<Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer integer, Integer integer2) {
                    return integer + integer2;
                }
            });
        }

        int max = 0;
        Map.Entry<Integer, Integer> prev = null;
        for(Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (prev != null) {
                if (entry.getKey() == prev.getKey() + 1) {
                    max = Math.max(max, entry.getValue()+ prev.getValue());
                }
            }

            prev = entry;
        }

        return max;

    }

    public int findLHS(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
//        Set<Integer> seen = new HashSet<>();

        for (int x : nums) {
            counts.merge(x, 1, Integer::sum);
//            counts.merge(x - 1, 1, Integer::sum);
//            seen.add(x);
        }

        int max = 0;
        for (int x : counts.keySet()) {
            if (counts.containsKey(x + 1)) {
                max = Math.max(max, counts.get(x) + counts.get(x + 1));
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LHS().findLHS(new int[]{1,1,1,1}));
    }
}
