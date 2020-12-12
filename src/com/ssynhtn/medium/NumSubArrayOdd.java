package com.ssynhtn.medium;

import java.util.Arrays;

class NumSubArrayOdd {
    public int numberOfSubarrays(int[] nums, int k) {
        int i = 0;
        int j = 0;
        int n = nums.length;

        boolean lastOdd = nums[n-1] % 2 != 0;
        while (i < n) {
            int i0 = i;
            while (i < n && nums[i] % 2 == 0) {
                i++;
            }

            nums[j++] = i - i0 + 1;
            if (i < n) {
                i++;
            }
//            System.out.println("now i " + i + " j " + j);
        }

        int l = 0;
        int r = l + k;
        int res = 0;

//        System.out.println("j " + j);
//        System.out.println(Arrays.toString(nums));
        while (r < j) {
            res += nums[r] * nums[l];
            r++;
            l++;
        }

        if (lastOdd) {
            res += nums[l];
        }
        
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new NumSubArrayOdd().numberOfSubarrays(new int[]{1,1,2,1,1}, 3));
    }
}