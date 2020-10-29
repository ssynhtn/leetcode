package com.ssynhtn.mock;

import java.util.Arrays;

public class PairSum {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int s = 0;
        for (int i = 0; i < nums.length; i+=2) {
            s += nums[i];
        }
        return s;
    }
}
