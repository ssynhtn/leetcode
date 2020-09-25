package com.ssynhtn.easy;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

public class LastStone {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for (int n : stones) {
            q.add(n);
        }

        while (q.size() > 1) {
            int y = q.poll();
            int x = q.poll();

            if (y > x) {
                q.add(y - x);
            }
        }

        if (q.isEmpty()) return 0;
        return q.peek();
    }
}
