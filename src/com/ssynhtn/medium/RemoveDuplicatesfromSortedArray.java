package com.ssynhtn.medium;

public class RemoveDuplicatesfromSortedArray {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 0;

        for (; j < nums.length; j++) {
            if (i < 2 || nums[j] != nums[i - 2]) {
                nums[i++] = nums[j];
            }
        }

        return i;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        System.out.println(new RemoveDuplicatesfromSortedArray().removeDuplicates(nums));
        for (int n : nums) {
            System.out.print(n + ", ");
        }

    }
}
