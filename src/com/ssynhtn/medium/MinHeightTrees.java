package com.ssynhtn.medium;

import java.util.*;

public class MinHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);
        if (n == 2) return Arrays.asList(0, 1);

        Set[] nodeNeis = new Set[n];
        for (int i = 0; i < n; i++) {
             nodeNeis[i] = new HashSet();
        }

        for (int[] e : edges) {
            nodeNeis[e[0]].add(e[1]);
            nodeNeis[e[1]].add(e[0]);
        }

        int remain = n;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (nodeNeis[i].size() == 1) {
                q.addLast(i);
            }
        }
        remain -= q.size();

        while (remain > 0) {
            int k = q.size();

            for (int i = 0; i < k; i++) {
                int x = q.removeFirst();
                int y = (int) nodeNeis[x].iterator().next();
                nodeNeis[y].remove(x);
                if (nodeNeis[y].size() == 1) {
                    q.addLast(y);
                }
            }
            remain -= q.size();
        }

        return new ArrayList<>(q);

    }
    public List<Integer> findMinHeightTreesOrig(int n, int[][] edges) {
        List<Set<Integer>> nodeNeis = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodeNeis.add(new HashSet<>());
        }

        for (int[] e : edges) {
            nodeNeis.get(e[0]).add(e[1]);
            nodeNeis.get(e[1]).add(e[0]);
        }

        List[] pair = findLongestPathAtAndFromPoint(nodeNeis, 0);
        List<Integer> longestPath = pair[0];
        int size = longestPath.size();
        List<Integer> res = new ArrayList<>();
        if (size % 2 == 0) {
            res.add(longestPath.get(size / 2 - 1));
            res.add(longestPath.get(size / 2));
        } else {
            res.add(longestPath.get(size / 2));
        }

        return res;
    }

    List[] findLongestPathAtAndFromPoint(List<Set<Integer>> nodeNeis, int x) {
        List<Integer> longestPathAtX = null;
        List<Integer> longestPathFromX = null;

        Set<Integer> neis = nodeNeis.get(x);
        if (neis.isEmpty()) {
            longestPathAtX = new ArrayList<>();
            longestPathFromX = new ArrayList<>();
            longestPathAtX.add(x);
            longestPathFromX.add(x);

            return new List[]{longestPathAtX, longestPathFromX};
        }

        for (int y : neis) {
            nodeNeis.get(y).remove(x);

            List[] subPair = findLongestPathAtAndFromPoint(nodeNeis, y);
            List<Integer> longestPathAtY = subPair[0];
            List<Integer> longestPathFromY = subPair[1];

            if (longestPathAtX == null) {
                longestPathFromY.add(x);
                longestPathFromX = longestPathFromY;
                if (longestPathFromX.size() > longestPathAtY.size()) {
                    longestPathAtX = longestPathFromX;
                } else {
                    longestPathAtX = longestPathAtY;
                }
            } else {
                int twoPathSize = longestPathFromX.size() + longestPathFromY.size();
                if (twoPathSize > longestPathAtX.size() && twoPathSize > longestPathAtY.size()) {
                    longestPathAtX = new ArrayList<>(longestPathFromX);
                    for (int i = longestPathFromY.size() - 1; i >= 0; i--) {
                        longestPathAtX.add(longestPathFromY.get(i));
                    }
                } else if (longestPathAtY.size() > longestPathAtX.size()) {
                    longestPathAtX = longestPathAtY;
                }

                longestPathFromY.add(x);
                if (longestPathFromY.size() > longestPathFromX.size()) {
                    longestPathFromX = longestPathFromY;
                }

            }
        }

        return new List[]{longestPathAtX, longestPathFromX};
    }
    private int countHeight(List<List<Integer>> nodeNeis, int x) {
        Set<Integer> visited = new HashSet<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.addLast(x);
        int h = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            h++;
            for (int i = 0; i < size; i++) {
                int y = q.removeFirst();
                visited.add(y);
                List<Integer> neis = nodeNeis.get(y);
                for (Integer nei : neis) {
                    if (!visited.contains(nei)) {
                        q.addLast(nei);
                    }
                }
            }
        }

        return h;
    }

    public static void main(String[] args) {
        System.out.println(new MinHeightTrees().findMinHeightTrees(6, new int[][]{
                {0,1},
                {0,2},
                {0,3},
                {3,4},
                {4,5},
        }));
    }
}
