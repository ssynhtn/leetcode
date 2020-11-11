package com.ssynhtn.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Set<Integer>> sets = new HashMap<>();

        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];

            Set<Integer> xSet = sets.get(x);
            Set<Integer> ySet = sets.get(y);

            if (xSet == null && ySet == null) {
                Set<Integer> one = new HashSet<>();
                one.add(x);
                one.add(y);
                sets.put(x, one);
                sets.put(y, one);
            } else if (xSet == null) {
                ySet.add(x);
                sets.put(x, ySet);
            } else if (ySet == null) {
                xSet.add(y);
                sets.put(y, xSet);
            } else {
                if (xSet == ySet) {
                    return e;
                }
                for (int u : xSet) {
                    sets.put(u, ySet);
                }
                ySet.addAll(xSet);
            }
        }

        return null;
    }
}