package com.ssynhtn.medium;

import com.ssynhtn.easy.HouseRobber;

import java.util.Arrays;

public class HouseRobberII {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        if (n == 3) return Math.max(nums[0], Math.max(nums[1], nums[2]));

        int[] dp = new int[n];

        dp[n-1] = nums[n-1];
        dp[n-2] = Math.max(nums[n-2], nums[n-1]);
        for (int i = n-3; i >= 1; i--) {
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
        }

        int[] dp2 = new int[n];
        dp2[n-2] = nums[n-2];
        if (n - 3 >= 2) {
            dp2[n-3] = Math.max(nums[n-3], nums[n-2]);
        }
        for (int i = n-4; i>= 2; i--) {
            dp2[i] = Math.max(nums[i] + dp2[i + 2], dp2[i + 1]);
        }

//        System.out.println(Arrays.toString(dp));
//        System.out.println(Arrays.toString(dp2));
        return Math.max(dp[1], nums[0] + dp2[2]);

    }

    public static void main(String[] args) {
        System.out.println(new HouseRobberII().rob(new int[]{4,1,2,7,5,3,1}));
    }
}
