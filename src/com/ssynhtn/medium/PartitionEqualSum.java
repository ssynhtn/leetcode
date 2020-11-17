package com.ssynhtn.medium;

import java.util.Arrays;
import java.util.Collections;

public class PartitionEqualSum {
    public boolean canPartition(int[] nums) {
        final int n = nums.length;
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }

        if (sum % 2 != 0) return false;
        int target = sum / 2;

        Arrays.sort(nums);

        // dp[i][t] is there subset from first i elems that sum to t
        int[][] dp = new int[n+1][target + 1];

        return hasSubSet(nums, dp, n, target) == 1;
    }

    private int hasSubSet(int[] nums, int[][] dp, int i, int target) {
        if (target < 0) return -1;
        if (dp[i][target] != 0) return dp[i][target];

        if (target == 0) {
            dp[i][target] = 1;
            return 1;
        }

        if (i == 0) {
            dp[i][target] = -1;
            return -1;
        }


        if (hasSubSet(nums, dp, i-1, target - nums[i-1]) == 1) {
            dp[i][target] = 1;
            return 1;
        }


        int j = i-1;
        while (j > 0 && nums[j-1] == nums[j]) {
            j--;
        }

        if (hasSubSet(nums, dp, j, target) == 1) {
            dp[i][target] = 1;
            return 1;
        }


        dp[i][target] = -1;
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new PartitionEqualSum().canPartition(new int[]{1,5,11,5}));
    }
}