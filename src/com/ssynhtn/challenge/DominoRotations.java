package com.ssynhtn.challenge;

public class DominoRotations {
    public int minDominoRotations(int[] A, int[] B) {
        int count = Integer.MAX_VALUE;
        count = Math.min(count, countRotation(A, B, A[0]));
        count = Math.min(count, countRotation(A, B, B[0]));
        count = Math.min(count, countRotation(B, A, A[0]));
        count = Math.min(count, countRotation(B, A, B[0]));

        if (count != Integer.MAX_VALUE) return count;
        return -1;
    }

    private int countRotation(int[] a, int[] b, int target) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) continue;
            if (b[i] == target) {
                count++;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new DominoRotations().minDominoRotations(new int[]{3,5,1,2,3}, new int[]{3,6,3,3,4}));
    }
}
