package com.ssynhtn.contest;

import com.sun.corba.se.internal.Interceptors.PIORB;

import java.nio.channels.Pipe;
import java.util.*;

public class MinEffortPath {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    int[][] efforts;
    public int minimumEffortPath(int[][] heights) {
        // heap
        int n = heights.length;
        int m = heights[0].length;
        int tx = n-1;
        int ty = m-1;

        efforts = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                efforts[i][j] = Integer.MAX_VALUE;
            }
        }
        efforts[0][0] = 0;

        PriorityQueue<Point> heap = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int e1 = efforts[o1.x][o1.y];
                int e2 = efforts[o2.x][o2.y];
                return Integer.compare(e1, e2);
            }
        });

        heap.add(new Point(0, 0));
        while (!heap.isEmpty()) {
            Point p = heap.poll();
            int e = efforts[p.x][p.y];
            if (p.x == tx && p.y == ty) {
                return e;
            }

            if (p.x > 0) {
                int nextE = Math.max(Math.abs(heights[p.x][p.y] - heights[p.x - 1][p.y]), e);
                if (nextE < efforts[p.x-1][p.y]) {
                    Point next = new Point(p.x-1, p.y);
                    heap.remove(next);
                    efforts[p.x-1][p.y] = nextE;
                    heap.add(next);
                }
            }
            if (p.x + 1 < n) {
                int nextE = Math.max(Math.abs(heights[p.x][p.y] - heights[p.x + 1][p.y]), e);
                if (nextE < efforts[p.x + 1][p.y]) {
                    Point next = new Point(p.x + 1, p.y);
                    heap.remove(next);
                    efforts[p.x + 1][p.y] = nextE;
                    heap.add(next);
                }
            }

            if (p.y > 0) {
                int nextE = Math.max(Math.abs(heights[p.x][p.y] - heights[p.x][p.y-1]), e);
                if (nextE < efforts[p.x][p.y-1]) {
                    Point next = new Point(p.x, p.y-1);
                    heap.remove(next);
                    efforts[p.x][p.y-1] = nextE;
                    heap.add(next);
                }
            }
            if (p.y + 1 < m) {
                int nextE = Math.max(Math.abs(heights[p.x][p.y] - heights[p.x][p.y+1]), e);
                if (nextE < efforts[p.x][p.y+1]) {
                    Point next = new Point(p.x, p.y+1);
                    heap.remove(next);
                    efforts[p.x][p.y+1] = nextE;
                    heap.add(next);
                }
            }


        }

        return Integer.MAX_VALUE;
    }

    public int minimumEffortPathNoCache(int[][] heights) {
//        Set<Position> visited = new HashSet<>();
//        visited.add(new Position(0, 0));
        int n = heights.length;
        int m = heights[0].length;

        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        return findMinPath(heights, visited, 0, 0, 0, heights.length - 1, heights[0].length - 1, n, m, Integer.MAX_VALUE);
    }

    private int findMinPath(int[][] heights, boolean[][] visited, int effort, int x, int y, int tx, int ty, int n, int m, int currentMin) {
        if (effort >= currentMin) return currentMin;
        if (x == tx && y == ty) {
            return effort;
        }

        if (x > 0 && !visited[x - 1][y]) {
            visited[x - 1][y] = true;
            currentMin = findMinPath(heights, visited, Math.max(effort, Math.abs(heights[x][y] - heights[x - 1][y])), x - 1, y, tx, ty, n, m, currentMin);
            visited[x - 1][y] = false;
        }

        if (x + 1 < n && !visited[x + 1][y]) {
            visited[x + 1][y] = true;
            currentMin = findMinPath(heights, visited, Math.max(effort, Math.abs(heights[x][y] - heights[x + 1][y])), x + 1, y, tx, ty, n, m, currentMin);
            visited[x + 1][y] = false;
        }
        if (y > 0 && !visited[x][y - 1]) {
            visited[x][y - 1] = true;
            currentMin = findMinPath(heights, visited, Math.max(effort, Math.abs(heights[x][y] - heights[x][y - 1])), x, y - 1, tx, ty, n, m, currentMin);
            visited[x][y - 1] = false;
        }

        if (y + 1 < m && !visited[x][y + 1]) {
            visited[x][y + 1] = true;
            currentMin = findMinPath(heights, visited, Math.max(effort, Math.abs(heights[x][y] - heights[x][y + 1])), x, y + 1, tx, ty, n, m, currentMin);
            visited[x][y + 1] = false;
        }

        return currentMin;
    }

    public static void main(String[] args) {

        System.out.println(new MinEffortPath().minimumEffortPath(new int[][]{{8, 3, 2, 5, 2, 10, 7, 1, 8, 9}, {1, 4, 9, 1, 10, 2, 4, 10, 3, 5}, {4, 10, 10, 3, 6, 1, 3, 9, 8, 8}, {4, 4, 6, 10, 10, 10, 2, 10, 8, 8}, {9, 10, 2, 4, 1, 2, 2, 6, 5, 7}, {2, 9, 2, 6, 1, 4, 7, 6, 10, 9}, {8, 8, 2, 10, 8, 2, 3, 9, 5, 3}, {2, 10, 9, 3, 5, 1, 7, 4, 5, 6}, {2, 3, 9, 2, 5, 10, 2, 7, 1, 8}, {9, 10, 4, 10, 7, 4, 9, 3, 1, 6}}));
    }
}
