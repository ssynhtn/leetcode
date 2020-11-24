package com.ssynhtn.hard;

import java.util.Arrays;

// nlognlogn
class CountRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] buffer = new long[n];
        return countRangeSum(nums, buffer, 0, n-1, lower, upper);
    }

    private int countRangeSum(int[] nums, long[] buffer, int left, int right, int lower, int upper) {
        if (left > right) return 0;
        if (left == right) {
            return nums[left] >= lower && nums[left] <= upper ? 1 : 0;
        }

        int mid = left + (right - left) / 2;
        int res = countRangeSum(nums, buffer, left, mid, lower, upper) + countRangeSum(nums, buffer, mid + 1, right, lower, upper);

        buffer[mid] = nums[mid];
        for (int i = mid - 1; i >= left; i--) {
            buffer[i] = buffer[i+1] + nums[i];
        }

        buffer[mid + 1] = nums[mid + 1];
        for (int i = mid + 2; i <= right; i++) {
            buffer[i] = buffer[i-1] + nums[i];
        }

        Arrays.sort(buffer, mid + 1, right + 1);
        for (int i = mid; i >= left; i--) {
            long s = buffer[i];
            long min = lower - s;
            long max = upper - s;

            if (min > buffer[right]) continue;
            if (max < buffer[mid + 1]) continue;
            int minIndex = bSearch(buffer, min, mid + 1, right);
            int maxIndex = bSearchMax(buffer, max, mid + 1, right);

            if (maxIndex >= minIndex) {
                res += maxIndex - minIndex + 1;
            }
        }

        return res;
    }

    // find >= min 的最小index
    int bSearch(long[] buffer, long min, int left, int right) {
        while (right - left >= 2) {
            int mid = left + (right - left) / 2;
            if (buffer[mid] >= min) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return buffer[left] >= min ? left : right;
    }

    // find <= max 的最大index
    int bSearchMax(long[] buffer, long max, int left, int right) {
        while (right - left >= 2) {
            int mid = left + (right - left) / 2;
            if (buffer[mid] <= max) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return buffer[right] <= max ? right : left;
    }

    public static void main(String[] args) {
        System.out.println(new CountRangeSum().countRangeSum(new int[]{-3,1,2,-2,2,-1}, -3, -1));
    }
}