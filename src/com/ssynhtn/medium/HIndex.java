package com.ssynhtn.medium;

import javax.crypto.spec.PSource;
import java.util.Arrays;

public class HIndex {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int i = citations.length - 1;
        int h = 1;

        while (i >= 0 && citations[i] >= h) {
            h++;
            i--;
        }

        return h - 1;
    }

    public static void main(String[] args) {
        System.out.println(new HIndex().hIndex(new int[]{3,0,6,1,5}));
    }
}
