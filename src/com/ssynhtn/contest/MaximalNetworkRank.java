package com.ssynhtn.contest;

import java.util.*;
import java.util.concurrent.Phaser;

public class MaximalNetworkRank {

    public int maximalNetworkRank(int n, int[][] roads) {

        List<Set<Integer>> neis = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            neis.add(new HashSet<>());
        }

        for (int[] road : roads) {
            neis.get(road[0]).add(road[1]);
            neis.get(road[1]).add(road[0]);
        }

        Map<Integer, Set<Integer>> topNodes = new HashMap<>();
        Map<Integer, Set<Integer>> secondNodes = new HashMap<>();

        int max = 0;
        int second = 0;
        for (int x = 0; x < n; x++) {
            Set<Integer> xNeis = neis.get(x);
            if (xNeis.size() > max) {
                second = max;
                max = xNeis.size();
                secondNodes = topNodes;
                topNodes = new HashMap<>();
                topNodes.put(x, xNeis);
            } else if (xNeis.size() == max) {
                topNodes.put(x, xNeis);
            } else if (xNeis.size() > second) {
                second = xNeis.size();
                secondNodes.clear();
                secondNodes.put(x, xNeis);
            } else if (xNeis.size() == second) {
                secondNodes.put(x, xNeis);
            }
        }

        if (topNodes.size() == 1) {
//            System.out.println("single top node " + topNodes.keySet().iterator().next());
            Set<Integer> next = secondNodes.keySet();
            Set<Integer> topNeis = topNodes.values().iterator().next();

//            System.out.println("top neis " + topNeis);
//            System.out.println("next nodes " + next);

            if (topNeis.containsAll(next)) {
//                System.out.println("top is connected to all next ");
                return max + second - 1;
            }
//            System.out.println("top is not connected to all next");
            return max + second;
        }

        List<Integer> topList = new ArrayList<>(topNodes.keySet());
//        System.out.println("we have " + topNodes.size() + " top nodes " + topNodes);
        while (topList.size() > 1) {
            int last = topList.remove(topList.size() - 1);
            if (!topNodes.get(last).containsAll(topList)) {
//                System.out.println("last " + last + "'s neis dont contain all other top nodes ");
                return max * 2;
            }
        }

//        System.out.println("all top nodes are inter connected");
        return max * 2 - 1;

    }

    public static void main(String[] args) {
        System.out.println(new MaximalNetworkRank().maximalNetworkRank(5, new int[][]{
                {0, 1},
                {0, 3},
                {1, 2},
                {1, 3},
                {2, 3},
                {2, 4},
        }));
    }
}
