package com.ssynhtn.contest;

class Moutain {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < x) {
                    max = Math.max(max, left[j]);
                }
            }
            left[i] = max + 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            int max = 0;
            for (int j = n - 1; j > i; j--) {
                if (nums[j] < x) {
                    max = Math.max(max, right[j]);
                }
            }
            right[i] = max + 1;
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (left[i] > 1 && right[i] > 1) {
                max = Math.max(left[i] + right[i] - 1, max);
            }
        }

        return n - max;
    }

    public static void main(String[] args) {
        System.out.println(new Moutain().minimumMountainRemovals(new int[]{9,8,1,7,6,5,4,3,2,1}));
    }
}