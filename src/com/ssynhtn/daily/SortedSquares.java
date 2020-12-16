package com.ssynhtn.daily;

import java.util.Arrays;

class SortedSquares {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * nums[i];
        }
        int[] res = new int[n];

        int i = 0;
        int j = n-1;
        int k = n-1;

        while (i <= j) {
            if (nums[i] > nums[j]) {
                res[k--] = nums[i];
                i++;
            } else {
                res[k--] = nums[j];
                j--;
            }
        }

        return res;
    }

    public int[] sortedSquaresBinary(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        
        int index = Arrays.binarySearch(nums, 0);
        if (index < 0) {
            index = -1 - index;
        }

        //  [0, index) <= 0 <= [index, n)
        int i = index - 1;
        int j = index;
        int k = 0;
        while (i >= 0 && j < n) {
            if (-nums[i] < nums[j]) {
                res[k++] = nums[i] * nums[i];
                i--;
            } else {
                res[k++] = nums[j] * nums[j];
                j++;
            }
        }

        while (i >= 0) {
            res[k++] = nums[i] * nums[i];
            i--;
        }

        while (j < n) {
            res[k++] = nums[j] * nums[j];
            j++;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SortedSquares().sortedSquares(new int[]{-7,-3,2,3,11})));
    }
}