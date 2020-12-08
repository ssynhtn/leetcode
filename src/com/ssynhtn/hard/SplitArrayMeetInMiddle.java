package com.ssynhtn.hard;

import com.ssynhtn.common.MathUtils;
import kotlin.collections.UCollectionsKt;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class SplitArrayMeetInMiddle {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        if (n <= 1) return false;
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }

        int d = MathUtils.gcd(sum, n);
        if (d == 1) return false;

        int s = sum / d;
        int m = n / d;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * m - s;
        }

        int mid = n / 2;
        Set<Integer> sum1 = new HashSet<>();
        int leftSum = collect(nums, 0, mid, 0, sum1, true, true);
        Set<Integer> sum2 = new HashSet<>();
        int rightSum = collect(nums, mid, n, 0, sum2, true, true);
//
//        System.out.println("left " + leftSum);
//        System.out.println("right " + rightSum);
//        System.out.println("left sums " + sum1);
//        System.out.println("right sums " + sum2);

        if (leftSum == 0) return true;
        if (sum1.contains(0)) return true;
        if (rightSum == 0) return true;
        if (sum2.contains(0)) return true;

        if (sum2.contains(-leftSum)) return true;
        for (int x : sum1) {
            if (sum2.contains(-x)) return true;
        }

        return false;
    }

    private int collect(int[] nums, int start, int end, int prefix, Set<Integer> sums, boolean usedAll, boolean usedNone) {
        if (start == end) {
            if (!usedAll && !usedNone) {
                sums.add(prefix);
            }
            return 0;
        }
        collect(nums, start + 1, end, prefix, sums, false, usedNone);
        return collect(nums, start + 1, end, prefix + nums[start], sums, usedAll, false) + nums[start];
    }


    public static void main(String[] args) {
        System.out.println(new SplitArrayMeetInMiddle().splitArraySameAverage(new int[]{3, 1}));
    }


}