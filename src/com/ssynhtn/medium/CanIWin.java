package com.ssynhtn.medium;

import java.util.HashMap;
import java.util.Map;

public class CanIWin {
    // 111111
    public boolean canIWin(int n, int target) {
        int sum = n*(n+1)/2;
        if (sum < target) return false;
        byte[] map = new byte[1<<n];

        int key = 0;
        for (int i = 0; i < n; i++) {
            key |= (1 << i);
        }

//        System.out.println("key " + Integer.toBinaryString(key));
        return win(map, key, 0, target, n);
        
    }
    
    boolean win(byte[] map, int key, int sum, int target, int potentialMax) {
        byte res = map[key];
        if (res != 0) return res == 1;

        int max = -1;
        for (int i = potentialMax; i >= 1; i--) {
            int iMask = 1<<(i-1);
            if ((key & iMask) != 0) {
                // i有
                if (max == -1) {
                    max = i;
                }
                if (i + sum >= target) {
                    map[key] = 1;
                    return true;
                }
                int nextKey = (key & ~(iMask));
//                System.out.println("next target " + (target - i) + ", next key after removing " + i + ", " + Integer.toBinaryString(nextKey));
                if (!win(map, nextKey, i + sum, target, i == max ? max - 1 : max)) {
                    map[key] = 1;
                    return true;
                }
            }
        }
        
        map[key] = 0;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new CanIWin().canIWin(10, 11));
    }
}