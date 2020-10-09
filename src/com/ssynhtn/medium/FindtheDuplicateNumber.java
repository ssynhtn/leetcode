package com.ssynhtn.medium;

import java.util.HashSet;
import java.util.Set;

public class FindtheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        for (int x : nums) {
            int index = x - 1;
            if (nums[index] > n) return x > n ? x - n : x;
            nums[index] += n;
        }
        return -1;
    }
}
