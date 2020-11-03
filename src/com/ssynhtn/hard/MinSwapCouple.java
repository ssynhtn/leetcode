package com.ssynhtn.hard;

import java.util.ArrayDeque;

public class MinSwapCouple {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int res = 0;

        int[] posMap = new int[n];
        for (int i = 0; i < n; i++) {
            posMap[row[i]] = i;
        }

        boolean[] visited = new boolean[n];
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (visited[row[i]]) continue;
            int count = 1;
            q.addLast(row[i]);
            visited[row[i]] = true;

            while (!q.isEmpty()) {
                int x = q.removeLast();
                int pos = posMap[x];

                int next;
                if (pos % 2 == 0) {
                    next = row[pos + 1];
                } else {
                    next = row[pos - 1];
                }

                if (!visited[next]) {
                    visited[next] = true;
                    q.addLast(next);
                    count++;
                }

                int y;
                if (x % 2 == 0) {
                    y = x + 1;
                } else {
                    y = x - 1;
                }
                if (!visited[y]) {
                    visited[y] = true;
                    q.addLast(y);
                    count++;
                }
            }

            res += count/2 - 1;
        }


        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MinSwapCouple().minSwapsCouples(new int[]{3,2,0,1}));
    }
}
