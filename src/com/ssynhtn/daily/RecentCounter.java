package com.ssynhtn.daily;

import java.util.ArrayDeque;
import java.util.Queue;

public class RecentCounter {
    ArrayDeque<Integer> times = new ArrayDeque<>();
    public RecentCounter() {

    }

    public int ping(int t) {
        int old = t - 3000;
        while (times.peek() != null && times.peek() < old) {
            times.removeFirst();
        }

        times.addLast(t);

        return times.size();
    }
}
