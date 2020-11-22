package com.ssynhtn.contest;

import java.util.Arrays;
import java.util.Comparator;

public class MinEffort {
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, new Comparator<int[]>() {
            @Override
            public int compare(int[] one, int[] two) {
                int diff = one[1] - one[0];
                int diff2 = two[1] - two[0];
                return diff - diff2;
            }
        });

        int sum = 0;
        for (int[] pair : tasks) {
            sum = Math.max(sum + pair[0], pair[1]);
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new MinEffort().minimumEffort(new int[][]{{1,7},{2,8},{3,9},{4,10},{5,11},{6,12}}));
    }
}