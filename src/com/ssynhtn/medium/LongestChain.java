package com.ssynhtn.medium;

import java.util.Arrays;
import java.util.Comparator;

public class LongestChain {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0]) return -1;
                if (o1[0] > o2[0]) return 1;

                return o1[1] - o2[1];
            }
        });

        int count = 0;
        int i = 0;
        int n = pairs.length;
        while (i < n) {
            int j = i + 1;
            boolean foundSmaller = false;
            while (j < n && pairs[j][0] <= pairs[i][1]) {
                if (pairs[j][1] <= pairs[i][1]) {
                    foundSmaller = true;
                    break;
                }
                j++;
            }

            if (foundSmaller) {
                i++;
                continue;
            }

            count++;
            i = j;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new LongestChain().findLongestChain(new int[][]{{-6,9},{1,6},{8,10},{-1,4},{-6,-2},{-9,8},{-5,3},{0,3}}));
    }
}
