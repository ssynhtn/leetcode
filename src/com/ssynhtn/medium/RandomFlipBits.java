package com.ssynhtn.medium;

import java.util.Arrays;

class RandomFlipBits {

    /*
     * (n*m + 31) / 32
     *
     * (i, j) => i*m + j
     *
     */

    int[] bits;
    final int size;
    final int m;

    int zeroSize;
    int randomValue;
    int randomRemain;
    public RandomFlipBits(int n_rows, int n_cols) {
        m = n_cols;
        zeroSize = size = n_rows * n_cols;
        bits = new int[(size + 31) / 32];
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


        int zeroIndex = randomValue % zeroSize;
        randomValue = randomValue / zeroSize;
        zeroSize--;
        randomRemain--;

        // 这段的线性查找性能不行
        int i = 0;
        int zeroBits = 0;
        int iZeroCount;
        while (zeroBits + (iZeroCount = 32 - Integer.bitCount(bits[i])) <= zeroIndex) {
            i++;
            zeroBits += iZeroCount;
        }

        int target = bits[i];
        int flag = Integer.MIN_VALUE;
        int j = 0;
        for (; j < 32; j++) {
            if ((target & flag) == 0) {
                zeroBits++;
                if (zeroBits == zeroIndex + 1) {
                    bits[i] |= flag;
                    break;
                }
            }
            flag = flag >>> 1;
        }

        int index = i * 32 + j;
        return new int[]{index / m, index % m};
    }
    
    public void reset() {
        Arrays.fill(bits, 0);
        zeroSize = size;
        randomValue = 0;
        randomRemain = 0;
    }

    public static void main(String[] args) {
        RandomFlipBits flipBits = new RandomFlipBits(2, 3);
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