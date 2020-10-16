package com.ssynhtn.easy;

public class HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n];

        dp[n-1] = nums[n-1];
        if (n >= 2) {
            dp[n-2] = Math.max(nums[n-1], nums[n-2]);
        }

        for (int i = n - 3; i >= 0; i--) {
            dp[i] = Math.max(nums[i] + dp[i + 2], dp[i + 1]);
        }

        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new HouseRobber().rob(new int[]{1, 2, 3, 1}));
    }
}
