package com.ssynhtn.medium;

class SumMax {
    static final int M = 1000000007;
    public int kConcatenationMaxSum(int[] arr, int k) {
        int n = arr.length;
        int min = 0;
        int max = 0;
        int sum = 0;
        int minIndex = -1;
        int maxIndex = -1;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            arr[i] = sum;
            if (sum >= max) {
                max = sum;
                maxIndex = i;
            } else if (sum < min) {
                min = sum;
                minIndex = i;
            }
        }

        if (k == 1 || sum <= 0) {
            if (maxIndex > minIndex) {
                return max - min;
            }

            min = 0;
            int maxDiff = 0;
            for (int j : arr) {
                if (j > min) {
                    maxDiff = Math.max(maxDiff, j - min);
                } else {
                    min = j;
                }
            }

            if (k == 1) {
                return maxDiff;
            }

            return Math.max(maxDiff, (int) (((long) sum - min + max) % M));

        } else {
            return (int) ((long) sum - min + max + (k-2) * (long) sum % M);
        }
    }

    public static void main(String[] args) {
        System.out.println(new SumMax().kConcatenationMaxSum(new int[]{1,2}, 3));
        System.out.println(new SumMax().kConcatenationMaxSum(new int[]{1,-2, 1}, 5));
        System.out.println(new SumMax().kConcatenationMaxSum(new int[]{-1, -2}, 7));
    }
}