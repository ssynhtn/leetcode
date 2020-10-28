package com.ssynhtn.mock;

public class MaxDist {
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int i = 0;
        while (seats[i] == 0) {
            i++;
        }

        int max = i;
        int j = i + 1;
        while (j < n) {
            while (j < n && seats[j] == 0) {
                j++;
            }

            if (j < n) {
                max = Math.max(max, (j - i) / 2);
                i = j;
                j = j + 1;
            } else {
                max = Math.max(max, n-1 - i);
            }
        }



        return max;
    }
}
