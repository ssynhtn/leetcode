package com.ssynhtn.challenge;

public class SearchRotatedArray {
    public boolean search(int[] nums, int target) {
        for (int x : nums) {
            if (x == target) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 1000000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
        nums[0] = n;

        long start = System.currentTimeMillis();
        new SearchRotatedArray().search(nums, n-1);
        long end = System.currentTimeMillis();
        System.out.println("used " + (end - start) + " mills");
    }
}
