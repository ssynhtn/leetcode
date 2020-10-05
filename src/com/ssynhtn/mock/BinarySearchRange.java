package com.ssynhtn.mock;

public class BinarySearchRange {
    public int[] searchRange(int[] nums, int target) {
        int l = 0;
        int h = nums.length - 1;

        int rightMostSmallIndex = -1;
        while (l <= h) {
            int m = (l + h) / 2;
            if (nums[m] >= target) {
                h = m - 1;
                continue;
            }

            if (m == h || nums[m + 1] >= target) {
                rightMostSmallIndex = m;
                break;
            }

            l = m + 1;
        }

        int leftMostLargeIndex = nums.length;
        l = 0;
        h = nums.length - 1;
        while (l <= h) {
            int m = (l + h) / 2;
            if (nums[m] <= target) {
                l = m + 1;
                continue;
            }

            if (m == l || nums[m-1] <= target) {
                leftMostLargeIndex = m;
                break;
            }

            h = m - 1;
        }

        if (leftMostLargeIndex - rightMostSmallIndex == 1) return new int[]{-1, -1};
        return new int[]{rightMostSmallIndex + 1, leftMostLargeIndex - 1};
    }
}
