package com.ssynhtn.easy;

import java.util.Arrays;

/**
 * leetcode刷到230题到时候终于排行进入了前100k
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return;

        int n = nums.length;
        k = k % n;
        if (k == 0) return;

        int count = 0;
        int i = k;
        int last = nums[0];

        while (i != 0) {
            int temp = nums[i];
            nums[i] = last;
            last = temp;
            i = (i + k) % n;
            count++;
        }
        nums[0] = last;
        count++;

        if (count == n) return;
        int c = n / count;
        for (int j = 1; j < c; j++) {
            i = j + k;
            last = nums[j];

            while (i != j) {
                int temp = nums[i];
                nums[i] = last;
                last = temp;
                i = (i + k) % n;
            }
            nums[i] = last;
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        new RotateArray().rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}
