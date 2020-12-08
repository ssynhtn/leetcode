package com.ssynhtn.hard;

import com.ssynhtn.common.MathUtils;

import java.util.Arrays;

class SplitArray {
    public boolean splitArraySameAverage(int[] nums) {
        process(nums);
        Arrays.sort(nums);
        int n = nums.length;
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }

        int d = MathUtils.gcd(sum, n);
        if (d == 1) return false;

        for (int k = 1; k <= d/2; k++) {
            int target = sum / d * k;
            int count = n/d * k;

            if (find(nums, count, target, n-count, sum - target)) {
                return true;
            }
        }

        return false;

    }

    private void process(int[] nums) {
        int gcd = 0;
        for (int x : nums) {
            gcd = MathUtils.gcd(gcd, x);
        }

        if (gcd > 1) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] /= gcd;
            }
        }
    }

    boolean find(int[] nums, int count, int target, int oc, int ot) {
        return find(nums, 0, 0, 0, 0, count, target, oc, ot);
    }

    boolean find(int[] nums, int i, int select, int prefix, int oPrefix, int count, int target, int oc, int ot) {
        if (prefix > target) return false;
        if (oPrefix > ot) return false;
        if (select + nums.length - i < count) return false;
        if (select > count) return false;

        if (i == nums.length) {
            return select == count && prefix == target;
        }

        if (select == count) {
            return prefix == target;
        }

        int max = nums[nums.length - 1];
        if (prefix + max * (count - select) < target) return false;
        if (prefix + nums[i] * (count - select) > target) return false;

        if (find(nums, i + 1, select, prefix, oPrefix + nums[i], count, target, oc+1, ot)) {
            return true;
        }

        if (find(nums, i + 1, select + 1, prefix + nums[i], oPrefix, count, target, oc, ot)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new SplitArray().splitArraySameAverage(new int[]{60,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30}));
    }


}