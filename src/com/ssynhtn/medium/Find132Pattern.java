package com.ssynhtn.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Find132Pattern {
//    public boolean find132pattern(int[] nums) {
//
//        List<Integer> vallies = new ArrayList<>();
//        List<Integer> peaks = new ArrayList<>();
//
//        int i = 0;
//        int len = nums.length;
//
//        while (i + 1 < len && nums[i + 1] <= nums[i]) {
//            i++;
//        }
//
//        if (i + 1 == len) return false;
//        vallies.add(nums[i]);
//        while (i + 1 < len && nums[i + 1] >= nums[i]) {
//            i++;
//        }
//
//        if (i + 1 == len) return false;
//        peaks.add(nums[i]);
//
//        i++;
//        while (i < len) {
//            int x = nums[i];
//            for (int index = 0; index < vallies.size(); index++) {
//                if (x > vallies.get(index) && x < peaks.get(index)) {
//                    return true;
//                }
//
//                if (x >= peaks.get(index)) {
//
//                }
//            }
//        }
//
//
//        return false;
//    }
}
