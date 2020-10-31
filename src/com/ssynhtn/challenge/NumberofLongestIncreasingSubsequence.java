package com.ssynhtn.challenge;


public class NumberofLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] lens = new int[n];
        lens[n-1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i]) {
                    max = Math.max(max, lens[j]);
                }
            }
            lens[i] = max + 1;
        }

        int max = 1;
        for (int len :lens) {
            max = Math.max(max, len);
        }

        int[] counts = new int[n];
        counts[n-1] = 1;
        for (int i = n-2; i >= 0; i--) {
            if (lens[i] == 1) {
                counts[i] = 1;
            } else {
                for (int j = i + 1; j < n; j++) {
                    if (nums[j] > nums[i] && lens[j] == lens[i] - 1) {
                        counts[i] += counts[j];
                    }
                }
            }
        }

//        System.out.println(Arrays.toString(lens));
//        System.out.println(Arrays.toString(counts));
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (lens[i] == max) {
                count += counts[i];
            }
        }


        return count;
    }

    public static void main(String[] args) {
        System.out.println(new NumberofLongestIncreasingSubsequence().findNumberOfLIS(new int[]{1,3,5,4,7}));
    }
}
