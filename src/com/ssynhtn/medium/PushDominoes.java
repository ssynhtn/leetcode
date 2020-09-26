package com.ssynhtn.medium;

import java.util.*;

public class PushDominoes {
    public String pushDominoes(String dominoes) {
        char[] chs = dominoes.toCharArray();
        int i = 0;
        int j = 0;

        final int len = chs.length;

        while (true) {
            while (i < len && chs[i] != '.') {
                i++;
            }

            if (i == len) break;

            // i at start of .
            j = i + 1;
            while (j < len && chs[j] == '.') {
                j++;
            }

            // j at end of .
            if (i == 0 || chs[i - 1] == 'L') {
                if (j < len && chs[j] == 'L') {
                    // [i, j) to L
                    while (i < j) {
                        chs[i++] = 'L';
                    }
                }
            } else {
                if (j < len && chs[j] == 'L') {
                    // [i, j) to middle
                    int r = j - 1;
                    while (i < r) {
                        chs[i++] = 'R';
                        chs[r--] = 'L';
                    }

                } else {
                    // to R
                    while (i < j) {
                        chs[i++] = 'R';
                    }
                }
            }

            i = j;

        }

        return new String(chs);
    }

    public static void main(String[] args) {
        System.out.println(new PushDominoes().pushDominoes("RR.L"));
    }
}
