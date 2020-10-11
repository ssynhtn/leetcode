package com.ssynhtn.daily;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length <= 1) return false;
        if (k == 0) return false;
        if (k >= nums.length) {
            k = nums.length - 1;
        }

        Set<Integer> window = new HashSet<>();
        int i;
        for (i = 0; i <= k; i++) {
            if (!window.add(nums[i])) {
                return true;
            }
        }

        int j = 0;
        while (i < nums.length) {
            window.remove(nums[j]);
            if (!window.add(nums[i])) {
                return true;
            }
            i++;
            j++;
        }

        return false;
    }
}
