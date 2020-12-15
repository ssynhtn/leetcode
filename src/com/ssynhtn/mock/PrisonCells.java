package com.ssynhtn.mock;

import java.util.*;

class PrisonCells {
    
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        
        int key = toInt(cells);
        list.add(key);
        map.put(key, 0);
        
        
// i = 0
// int j
// while i < N
//     nextCell
//     i++
//     if (contains) break -> ith op returns itself, j = get
//     put(cell, i)

// j set: 
//     k = (N-j) % (i-j) + j, iterator, skip 
// j not set: then current cell state is

//        System.out.println("key " + Integer.toBinaryString(key));
        int i = 0;
        int j = -1;
        while (i < N){
            i++;
            key = ((~((key << 1) ^ (key >> 1))) & 0x7e);
//            System.out.println("key " + Integer.toBinaryString(key));
            if (map.containsKey(key)) {
                j = map.get(key);
                break;
            }
            
            map.put(key, i);
            list.add(key);
            
        }
        
        if (j != -1) {
            int k = (N-j) % (i-j) + j;
            key = list.get(k);

        }

        return toCell(key);
    
    }
    
    int toInt(int[] cells) {
        int res = 0;
        for (int x : cells) {
            res = res * 2 + x;
        }
        
        return res;
    }


    int[] toCell(int key) {
        int[] cells = new int[8];
        for (int i = 0; i < 8; i ++) {
            cells[i] = (key & (1 << (7 - i))) != 0 ? 1 : 0;
        }
        return cells;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new PrisonCells().prisonAfterNDays(new int[]{1,0,0,1,0,0,1,0}, 1000000000)));
    }
}