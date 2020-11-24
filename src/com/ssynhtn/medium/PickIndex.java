package com.ssynhtn.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class PickIndex {

    /*
    total m = N - bl.size()

0, 1, 2, ... m-1,   m, m+1, ... N-1

Map of B in [0, m) -> G in [m, n)



    */
    
    Map<Integer, Integer> b2g;
    final int m;
    public PickIndex(int N, int[] bl) {
        m = N - bl.length;
        int i = 0;
        int j = bl.length - 1;
        while (i <= j) {
            while (i <= j && bl[i] < m) {
                i++;
            }
            while (j >= i && bl[j] >= m) {
                j--;
            }
            
            if (i <= j) {
                int temp = bl[i];
                bl[i] = bl[j];
                bl[j] = temp;
                i++;
                j--;
            }
        }

        Arrays.sort(bl, i, bl.length);
        b2g = new HashMap<>();
        j = i;
        // [0, i) < m, [j,) >= m
        int next = m;
        for (int k = 0; k < i; k++) {
            while (j < bl.length && bl[j] == next) {
                j++;
                next++;
            }
            
            b2g.put(bl[k], next);
//            System.out.println("put " + bl[k] + " -> " + next);
            next++;
        }
        
        
    }
    
    public int pick() {
        int i = (int)(Math.random() * m);
        return b2g.getOrDefault(i, i);
    }

    public static void main(String[] args) {
        PickIndex pickIndex = new PickIndex(5, new int[]{1, 3,2});
        for (int i = 0; i < 10; i++) {
            System.out.println(pickIndex.pick());
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(N, blacklist);
 * int param_1 = obj.pick();
 */