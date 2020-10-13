package com.ssynhtn.mock;

import java.util.ArrayList;
import java.util.List;

public class IntervalIntersection {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();

        int i = 0;
        int j = 0;
        while (i < A.length && j < B.length) {
            int[] x = A[i];
            int[] y = B[j];

            if (x[1] < y[0]) {
                i++;
            } else if (y[1] < x[0]) {
                j++;
            } else {
                int[] joint = new int[2];
                joint[0] = Math.max(x[0], y[0]);
                joint[1] = Math.min(x[1], y[1]);
                res.add(joint);

                if (x[1] > y[1]) {
                    j++;
                } else if (y[1] > x[1]) {
                    i++;
                } else {
                    i++;
                    j++;
                }
            }
        }

        return res.toArray(new int[res.size()][]);
    }


}
