package com.ssynhtn.medium;

import java.util.Arrays;

class RandomFlipBitsReject {

    /*
     * (n*m + 31) / 32
     *
     * (i, j) => i*m + j
     *
     */

    int[] bits;
    final int size;
    final int m;

    int randomValue;
    int randomRemain;
    public RandomFlipBitsReject(int n_rows, int n_cols) {
        m = n_cols;
        size = n_rows * n_cols;
        bits = new int[(size + 31) / 32];
    }

    private int[] flip(int index) {
        int i = index / 32;
        int j = index % 32;
        int flag = Integer.MIN_VALUE >>> j;
        if ((bits[i] & flag) != 0) return null;
        bits[i] |= flag;
        return new int[]{index / m, index % m};
    }

    public int[] flip() {
        int[] res;
        while (true) {
            if (randomRemain == 0) {
                randomValue = 1;
                int subLimit = Integer.MAX_VALUE / size;
                while (randomValue <= subLimit) {
                    randomValue = randomValue * size;
                    randomRemain++;
                }

                randomValue = (int) (Math.random() * randomValue);
            }

            while (randomRemain > 0) {
                int index = randomValue % size;
                randomValue = randomValue / size;
                randomRemain--;

//                System.out.println("try index " + index + " rv " + randomValue + ", rm " + randomRemain);
                if ((res = flip(index)) != null) {
                    return res;
                }
            }

        }
    }
    
    public void reset() {
        Arrays.fill(bits, 0);
        randomValue = 0;
        randomRemain = 0;
    }

    public static void main(String[] args) {
        RandomFlipBitsReject flipBits = new RandomFlipBitsReject(2, 3);
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 6; i++) {
                System.out.println(Arrays.toString(flipBits.flip()));
            }
            flipBits.reset();
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n_rows, n_cols);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */