package com.ssynhtn.medium;

import java.util.Arrays;
import java.util.Comparator;

public class VideoStitching {
    public int videoStitching(int[][] clips, int T) {
        if (T == 0) return 0;

        Arrays.sort(clips, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        int n = clips.length;
        int r = 0;

        int count = 0;
        int i = 0;
        while (r < T) {
            int rMax = -1;
            while (i < n && clips[i][0] <= r) {
                rMax = Math.max(clips[i][1], rMax);
                i++;
            }

            if (rMax == -1) return -1;
            if (rMax > r) {
                r = rMax;
                count++;
            }
        }

        return count;


    }
}
