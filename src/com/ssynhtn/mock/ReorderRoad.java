package com.ssynhtn.mock;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class ReorderRoad {
    public int minReorder(int n, int[][] connections) {
        List<Integer>[] neis = new List[n];
        for (int i = 0; i < n; i++) {
            neis[i] = new ArrayList<>();
        }
        for (int[] edge : connections) {
            int a = edge[0];
            int b = edge[1];
            neis[a].add(b);
            neis[b].add(-a);
        }

        boolean[] visited = new boolean[n];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.addLast(0);
        visited[0] = true;
        int count = 0;
        while (!q.isEmpty()) {
            int a = q.removeFirst();
            for (int b : neis[a]) {
                if (visited[Math.abs(b)]) continue;
                if (b < 0) {
                    b = -b;
                } else {
                    count++;
                }
                q.addLast(b);
                visited[b] = true;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new ReorderRoad().minReorder(6, new int[][]{
                {0,1},
                {1,3},
                {2,3},
                {4,0},
                {4,5},
        }));
    }
}
