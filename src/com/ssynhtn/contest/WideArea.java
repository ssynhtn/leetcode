package com.ssynhtn.contest;

import java.util.Arrays;
import java.util.Comparator;

public class WideArea {
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int max = 0;
        for (int i = 0; i < points.length - 1; i++) {
            int a = points[i][0];
            int b = points[i+1][0];
            max = Math.max(b - a, max);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new WideArea().maxWidthOfVerticalArea(new int[][]{
                {3,1},{9,0},{1,0},{1,4},{5,3},{8,8}
        }));
    }
}
