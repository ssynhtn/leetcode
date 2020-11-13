package com.ssynhtn.easy;

import com.ssynhtn.hard.ValidNumber;

public class ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        return isValidABDC(p1, p2, p3, p4) || isValidABDC(p1, p3, p2, p4) || isValidABDC(p1, p3, p4, p2);

    }

    private boolean isValidABDC(int[] p1, int[] p2, int[] p3, int[] p4) {
        int x1 = p2[0] - p1[0];
        int y1 = p2[1] - p1[1];
        if (x1 == 0 && y1 == 0) return false;

        int x2 = p3[0] - p4[0];
        int y2 = p3[1] - p4[1];
        if (x1 != x2 || y1 != y2) return false;

        int u = p4[0] - p1[0];
        int v = p4[1] - p1[1];

        return x1 == -v && y1 == u || x1 == v && y1 == -u;
    }
}