package com.ssynhtn.mock;

public class PowerOfFour {
    public boolean isPowerOfFour(int num) {
        if (num == 0) return false;
        while (num != 1) {
            if ((num & 3) != 0) return false;
            num = num >>> 2;
        }

        return true;
    }

}
