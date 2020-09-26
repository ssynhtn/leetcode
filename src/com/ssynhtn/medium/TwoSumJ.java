package com.ssynhtn.medium;

import java.util.Arrays;

public class TwoSumJ {
    public int[] twoSum(int[] nums, int target) {
        int[] copy = nums.clone();
        Arrays.sort(copy);

        for (int i = 0; i < copy.length; i++) {
            int a = copy[i];
            int b = target - a;

            int index = Arrays.binarySearch(copy, i + 1, copy.length, b);
            if (index >= 0) {
                int aIndex = -1;
                int bIndex = -1;

                for (int j = 0; j < nums.length; j++) {
                    if (aIndex == -1 && nums[j] == a) {
                        aIndex = j;
                    } else if (nums[j] == b) {
                        bIndex = j;
                    }

                    if (aIndex >= 0 && bIndex >= 0) {
                        return new int[]{aIndex, bIndex};
                    }

                }
            }
        }

        return null;
    }

    public static void main(String[] args) {

    }

}
