package com.ssynhtn.hard;

import java.util.Arrays;

public class MaximumGap {
    public int maximumGap(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int diff = nums[i + 1] - nums[i];
            if (diff > max) {
                max = diff;
            }

        }

        return max;
    }
}
