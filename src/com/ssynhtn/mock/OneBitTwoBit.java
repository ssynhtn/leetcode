package com.ssynhtn.mock;

public class OneBitTwoBit {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        int m = bits.length - 1;
        while (i < m) {
            i += bits[i];
            i++;
        }

        return i == m;
    }
}
