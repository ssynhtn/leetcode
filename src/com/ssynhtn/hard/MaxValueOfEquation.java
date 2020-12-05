package com.ssynhtn.hard;

import java.util.ArrayDeque;

public class MaxValueOfEquation {
    public int findMaxValueOfEquation(int[][] points, int k) {
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;

        for (int[] p : points) {
            while (!stack.isEmpty() && p[0] - stack.peekFirst()[0] > k) {
                stack.removeFirst();
            }
            if (!stack.isEmpty()) {
                int[] left = stack.peekFirst();
                max = Math.max(max, left[1] - left[0] + p[0] + p[1]);
            }

            int d = p[1] - p[0];
            while (!stack.isEmpty()) {
                int[] last = stack.peekLast();
                if (last[1] - last[0] <= d) {
                    stack.removeLast();
                } else {
                    break;
                }
            }

            stack.addLast(p);
        }

        return max;

    }

    public static void main(String[] args) {
        System.out.println(new MaxValueOfEquation().findMaxValueOfEquation(new int[][]{
                {0,0},
                {3, 0},
                {9, 2},
        }, 3));
    }
}
