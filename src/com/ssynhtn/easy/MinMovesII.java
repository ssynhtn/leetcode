package com.ssynhtn.easy;

import java.util.Arrays;

public class MinMovesII {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        int i = 0;
        int j = nums.length - 1;
        while (j > i) {
            sum += nums[j] - nums[i];
            j--;
            i++;
        }
        return sum;
    }
}
