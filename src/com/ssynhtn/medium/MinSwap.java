package com.ssynhtn.medium;

public class MinSwap {
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        if (n <= 1) return 0;

        int minKeep = 0;
        int minSwap = 1;

        for (int i = 1; i < n; i++) {
            int nextKeep, nextSwap;
            if (Math.min(A[i], B[i]) > Math.max(A[i-1], B[i-1])) {
                nextKeep = Math.min(minKeep, minSwap);
                nextSwap = Math.min(minKeep, minSwap) + 1;
            } else {
                if (A[i] > A[i-1] && B[i] > B[i-1]) {
                    nextKeep = minKeep;
                    nextSwap = minSwap + 1;
                } else {
                    nextKeep = minSwap;
                    nextSwap = minKeep + 1;
                }
            }


            minKeep = nextKeep;
            minSwap = nextSwap;
        }

        return Math.min(minKeep, minSwap);
    }

    public static void main(String[] args) {
        System.out.println(new MinSwap().minSwap(new int[]{1, 3, 5, 4}, new int[]{1, 2, 3, 7}));
    }
}
