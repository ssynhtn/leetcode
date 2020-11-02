package com.ssynhtn.medium;

import java.util.*;

public class MakeConnections {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n-1) {
            return -1;
        }

        List<Integer>[] neis = new List[n];
        for (int i = 0; i < n; i++) {
            neis[i] = new ArrayList<>();
        }
        for (int[] conn : connections) {
            int a = conn[0];
            int b = conn[1];
            neis[a].add(b);
            neis[b].add(a);
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            count++;
            q.addLast(i);
            visited[i] = true;

            while (!q.isEmpty()) {
                int size = q.size();
                for (int j = 0; j < size; j++) {
                    int a = q.removeFirst();
                    List<Integer> aNeis = neis[a];
                    for (int aNei : aNeis) {
                        if (!visited[aNei]) {
                            q.addLast(aNei);
                            visited[aNei] = true;
                        }
                    }
                }
            }
        }


        // n nodes, count groups
        return count - 1;
    }

    public static void main(String[] args) {
        System.out.println(new MakeConnections().makeConnected(6, new int[][]{
                {0,1},{0,2},{0,3},{1,2},{1,3}
        }));
    }
}
