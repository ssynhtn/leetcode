package com.ssynhtn.mock;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int i = 0, j = 0;
        int n = nums.length;
        while (j < n) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
            j++;
        }
        return i;
    }
}