package com.ssynhtn.contest;

import java.util.*;

class MinDist {
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        Map<Integer, List<int[]>> edges = new HashMap<>();
        for (int[] edge : edgeList) {
            int x = edge[0];
            int y = edge[1];
            int d = edge[2];
            List<int[]> list = edges.computeIfAbsent(x, k -> new ArrayList<>());
            List<int[]> list2 = edges.computeIfAbsent(y, k -> new ArrayList<>());

            list.add(new int[]{y, d});
            list2.add(new int[]{x, d});
        }

        int m = queries.length;
        boolean[] res = new boolean[m];

        for (int i = 0; i < m; i++) {
            compute(res, edges, map, queries[i], i);
        }

        return res;
    }

    private void compute(boolean[] res, Map<Integer, List<int[]>> edges, Map<Integer, Map<Integer, Integer>> map, int[] query, int i) {
        int x = query[0];
        int y = query[1];
        int target = query[2];

        Map<Integer, Integer> xDistMap = map.computeIfAbsent(x, k -> new HashMap<>());

        if (xDistMap.containsKey(y)) {
            int dist = xDistMap.get(y);

            res[i] = dist < target;
            return;
        }


        Map<Integer, Integer> dist = new HashMap<>();
        PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return dist.get(a) - dist.get(b);
            }
        });

        dist.put(x, 0);
        q.add(x);

        while (!q.isEmpty()) {
            int u = q.remove();
            int d = dist.get(u);

            xDistMap.put(u, d);
            map.computeIfAbsent(u, k -> new HashMap<>()).put(x, d);

            if (u == y) {
                res[i] = d < target;
                return;
            }

            List<int[]> nexts = edges.get(u);
            if (nexts != null) {
                for (int[] next : nexts) {
                    int v = next[0];
                    int e = next[1];
                    int f = Math.max(e, d);
                    if (!dist.containsKey(v) || dist.get(v) > f) {
                        dist.put(v, f);
                        q.remove(v);
                        q.add(v);
                    }
                }
            }
        }



    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MinDist().distanceLimitedPathsExist(5, new int[][]{{0,1,10},{1,2,5},{2,3,9},{3,4,13}}, new int[][]{{0,4,14},{1,4,13}})));
        boolean[] res = new MinDist().distanceLimitedPathsExist(13, new int[][]
                        {{9,1,53},{3,2,66},{12,5,99},{9,7,26},{1,4,78},{11,1,62},{3,10,50},{12,1,71},{12,6,63},{1,10,63},{9,10,88},{9,11,59},{1,4,37},{4,2,63},{0,2,26},{6,12,98},{9,11,99},{4,5,40},{2,8,25},{4,2,35},{8,10,9},{11,9,25},{10,11,11},{7,6,89},{2,4,99},{10,4,63}},
                new int[][]
                        {{9,7,65},{9,6,1},{4,5,34},{10,8,43},{3,7,76},{4,2,15},{7,6,52},{2,0,50},{7,6,62},{1,0,81},{4,5,35},{0,11,86},{12,5,50},{11,2,2},{9,5,6},{12,0,95},{10,6,9},{9,4,73},{6,10,48},{12,0,91},{9,10,58},{9,8,73},{2,3,44},{7,11,83},{5,3,14},{6,2,33}}
        );
        System.out.println(Arrays.toString(res));

        boolean[] expected = {true,false,false,true,true,false,false,true,false,true,false,true,false,false,false,true,false,true,false,true,true,true,false,true,false,false};
        System.out.println("expected");
        System.out.println(Arrays.toString(expected));

        System.out.println("same? " + Arrays.equals(res, expected));
    }
}