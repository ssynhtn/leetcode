package com.ssynhtn.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class RandomFlipBitsMap {

    Map<Integer, Integer> indexMap = new HashMap<>();
    final int size;
    final int m;

    int zeroSize;
    int randomValue;
    int randomRemain;
    public RandomFlipBitsMap(int n_rows, int n_cols) {
        m = n_cols;
        zeroSize = size = n_rows * n_cols;
    }
    
    public int[] flip() {
        // zeroSize > 0
        if (randomRemain == 0) {
            randomValue = 1;
            int next = zeroSize;
            while (next > 0 && next <= Integer.MAX_VALUE / randomValue) {
                randomValue = randomValue * next;
                next--;
                randomRemain++;
            }

            randomValue = (int) (Math.random() * randomValue);
        }


        int i = randomValue % zeroSize;
        randomValue = randomValue / zeroSize;
        zeroSize--;
        randomRemain--;


//        if (i not in map)
//        res = i
//        i == k-1: do nothing
//        i < k -1:
//        k-1 map to some?
//                map[i] = remove[k-1]
//        map[i] = k-1
//else
//        res = map[i]
//        i == k-1: map.remove(i)
//        i < k -1:
//        k-1 map to some?
//                map[i] = remove[k-1]
//        map[i] = k-1
//
//

        int res = indexMap.getOrDefault(i, i);

        if (i == zeroSize) {
            indexMap.remove(i);
        } else {
            Integer lastValue = indexMap.remove(zeroSize);
            if (lastValue != null) {
                indexMap.put(i, lastValue);
            } else {
                indexMap.put(i, zeroSize);
            }
        }

        return new int[]{res / m, res % m};
    }
    
    public void reset() {
        indexMap.clear();
        zeroSize = size;
        randomValue = 0;
        randomRemain = 0;
    }

    public static void main(String[] args) {
        RandomFlipBitsMap flipBits = new RandomFlipBitsMap(2, 3);
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(flipBits.flip()));
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n_rows, n_cols);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */