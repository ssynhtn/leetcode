package com.ssynhtn.easy;

public class MinMoves {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int x : nums) {
            if (x < min) {
                min = x;
            }
            sum += x;
        }

        return sum - min * n;
    }
}
