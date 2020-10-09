package com.ssynhtn.mock;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        int major = 0;
        int i = 0;
        int count = 0;
        int len = nums.length;

        while (i < len) {
            major = nums[i];
            count = 1;
            i++;

            while (i < len) {
                if (nums[i++] == major) {
                    count++;
                } else {
                    count--;
                    if (count == 0) {
                        break;
                    }
                }
            }
        }

        return major;
    }
}
