package com.ssynhtn.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxConcatDigitsMerge {
    private void findMaxNum(int[] nums, int l1, int r1, int k, int[] out) {
        if (k == 0) return;
        int index = 0;
        int remain = r1 - l1;
        for (int i = l1; i < r1; i++) {
            while (index > 0 && nums[i] > out[index - 1] && remain > k) {
                index--;
                remain--;
            }

            if (index == k) {
                remain--;
                continue;
            }
            out[index++] = nums[i];
        }
//        while (k > 0) {
//            int d1 = -1, next1 = -1;
//
//            for (d1 = 9; d1 >= 0; d1--) {
//                next1 = nextIndex1[l1][d1];
//
//                if (next1 >= l1 && next1 <= r1 - k) {
//                    break;
//                }
//            }
//
//            out[index++] = d1;
//            k--;
//            l1 = next1 + 1;
//        }

    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
//        System.out.println("nums1 " + Arrays.toString(nums1));
//        System.out.println("nums2 " + Arrays.toString(nums2));
        int n = nums1.length;
        int m = nums2.length;


        int min = Math.max(0, k - m);
        int max = Math.min(n, k);

        int[] res = null;
        int[] out = new int[k];
        int[] buff1 = new int[k];
        int[] buff2 = new int[k];
        for (int k1 = min; k1 <= max; k1++) {
            findMaxNum(nums1, 0, n, k1, buff1);
            findMaxNum(nums2, 0, m, k - k1, buff2);
            merge(buff1, buff2, out, 0, 0, k1, 0, k - k1);

            if (res == null) {
                res = out;
                out = new int[k];
            } else if (larger(out, res)) {
                int[] temp = res;
                res = out;
                out = temp;
            }

        }
        return res;

    }

    // out > res
    private boolean larger(int[] out, int[] res) {
        int i = 0;
        int n = out.length;
        while (i < n && out[i] == res[i]) {
            i++;
        }

        return i < n && out[i] > res[i];
    }

    private int[][] buildNextIndex(int[] nums, int n) {
        int[][] next = new int[n+1][10];
        Arrays.fill(next[n], -1);
        for (int i = n - 1; i >= 0; i--) {
            System.arraycopy(next[i + 1], 0, next[i], 0, 10);
            next[i][nums[i]] = i;
        }

        return next;
    }

    private void merge(int[] nums1, int[] nums2, int[] out, int index, int i, int r1, int j, int r2) {
        while (i < r1 && j < r2) {
            int i0 = i;
            int j0 = j;
            while (i0 < r1 && j0 < r2 && nums1[i0] == nums2[j0]) {
                i0++;
                j0++;
            }

            if (i0 == r1) {
                out[index++] = nums2[j++];
            } else if (j0 == r2) {
                out[index++] = nums1[i++];
            } else if (nums1[i0] > nums2[j0]) {
                out[index++] = nums1[i++];
            } else {
                out[index++] = nums2[j++];
            }
        }

        while (i < r1) {
            out[index++] = nums1[i++];
        }
        while (j < r2) {
            out[index++] = nums2[j++];
        }
    }


    public static void main(String[] args) {
        int k = 2;
        int[] ones = new int[]{

        };
        int[] twos = new int[]{
                5,5,4,7
        };
        int[] buff = new int[2];
        new MaxConcatDigitsMerge().findMaxNum(twos, 0, 4, 2, buff);
        System.out.println(Arrays.toString(buff));
//
//        int k = 80;
//        int[] ones = new int[]{
//                1,5,8,1,4,0,8,5,0,7,0,5,7,6,0,5,5,2,4,3,6,4,6,6,3,8,1,1,3,1,3,5,4,3,9,5,0,3,8,1,4,9,8,8,3,4,6,2,5,4,1,1,4,6,5,2,3,6,3,5,4,3,0,7,2,5,1,5,3,3,8,2,2,7,6,7,5,9,1,2
//        };
//        int[] twos = new int[]{
//                7,8,5,8,0,1,1,6,1,7,6,9,6,6,0,8,5,8,6,3,4,0,4,6,7,8,7,7,7,5,7,2,5,2,1,9,5,9,3,7,3,9,9,3,1,4,3,3,9,7,1,4,4,1,4,0,2,3,1,3,2,0,2,4,0,9,2,0,1,3,9,1,2,2,6,6,9,3,6,0
//        };

//        int[] ones = new int[]{
//                8,9,7,3,5,9,1,0,8,5,3,0,9,2,7,4,8,9,8,1,0,2,0,2,7,2,3,5,4,7,4,1,4,0,1,4,2,1,3,1,5,3,9,3,9,0,1,7,0,6,1,8,5,6,6,5,0,4,7,2,9,2,2,7,6,2,9,2,3,5,7,4,7,0,1,8,3,6,6,3,0,8,5,3,0,3,7,3,0,9,8,5,1,9,5,0,7,9,6,8,5,1,9,6,5,8,2,3,7,1,0,1,4,3,4,4,2,4,0,8,4,6,5,5,7,6,9,0,8,4,6,1,6,7,2,0,1,1,8,2,6,4,0,5,5,2,6,1,6,4,7,1,7,2,2,9,8,9,1,0,5,5,9,7,7,8,8,3,3,8,9,3,7,5,3,6,1,0,1,0,9,3,7,8,4,0,3,5,8,1,0,5,7,2,8,4,9,5,6,8,1,1,8,7,3,2,3,4,8,7,9,9,7,8,5,2,2,7,1,9,1,5,5,1,3,5,9,0,5,2,9,4,2,8,7,3,9,4,7,4,8,7,5,0,9,9,7,9,3,8,0,9,5,3,0,0,3,0,4,9,0,9,1,6,0,2,0,5,2,2,6,0,0,9,6,3,4,1,2,0,8,3,6,6,9,0,2,1,6,9,2,4,9,0,8,3,9,0,5,4,5,4,6,1,2,5,2,2,1,7,3,8,1,1,6,8,8,1,8,5,6,1,3,0,1,3,5,6,5,0,6,4,2,8,6,0,3,7,9,5,5,9,8,0,4,8,6,0,8,6,6,1,6,2,7,1,0,2,2,4,0,0,0,4,6,5,5,4,0,1,5,8,3,2,0,9,7,6,2,6,9,9,9,7,1,4,6,2,8,2,5,3,4,5,2,4,4,4,7,2,2,5,3,2,8,2,2,4,9,8,0,9,8,7,6,2,6,7,5,4,7,5,1,0,5,7,8,7,7,8,9,7,0,3,7,7,4,7,2,0,4,1,1,9,1,7,5,0,5,6,6,1,0,6,9,4,2,8,0,5,1,9,8,4,0,3,1,2,4,2,1,8,9,5,9,6,5,3,1,8,9,0,9,8,3,0,9,4,1,1,6,0,5,9,0,8,3,7,8,5
//        };
//        int[] twos = new int[]{
//                7,8,4,1,9,4,2,6,5,2,1,2,8,9,3,9,9,5,4,4,2,9,2,0,5,9,4,2,1,7,2,5,1,2,0,0,5,3,1,1,7,2,3,3,2,8,2,0,1,4,5,1,0,0,7,7,9,6,3,8,0,1,5,8,3,2,3,6,4,2,6,3,6,7,6,6,9,5,4,3,2,7,6,3,1,8,7,5,7,8,1,6,0,7,3,0,4,4,4,9,6,3,1,0,3,7,3,6,1,0,0,2,5,7,2,9,6,6,2,6,8,1,9,7,8,8,9,5,1,1,4,2,0,1,3,6,7,8,7,0,5,6,0,1,7,9,6,4,8,6,7,0,2,3,2,7,6,0,5,0,9,0,3,3,8,5,0,9,3,8,0,1,3,1,8,1,8,1,1,7,5,7,4,1,0,0,0,8,9,5,7,8,9,2,8,3,0,3,4,9,8,1,7,2,3,8,3,5,3,1,4,7,7,5,4,9,2,6,2,6,4,0,0,2,8,3,3,0,9,1,6,8,3,1,7,0,7,1,5,8,3,2,5,1,1,0,3,1,4,6,3,6,2,8,6,7,2,9,5,9,1,6,0,5,4,8,6,6,9,4,0,5,8,7,0,8,9,7,3,9,0,1,0,6,2,7,3,3,2,3,3,6,3,0,8,0,0,5,2,1,0,7,5,0,3,2,6,0,5,4,9,6,7,1,0,4,0,9,6,8,3,1,2,5,0,1,0,6,8,6,6,8,8,2,4,5,0,0,8,0,5,6,2,2,5,6,3,7,7,8,4,8,4,8,9,1,6,8,9,9,0,4,0,5,5,4,9,6,7,7,9,0,5,0,9,2,5,2,9,8,9,7,6,8,6,9,2,9,1,6,0,2,7,4,4,5,3,4,5,5,5,0,8,1,3,8,3,0,8,5,7,6,8,7,8,9,7,0,8,4,0,7,0,9,5,8,2,0,8,7,0,3,1,8,1,7,1,6,9,7,9,7,2,6,3,0,5,3,6,0,5,9,3,9,1,1,0,0,8,1,4,3,0,4,3,7,7,7,4,6,4,0,0,5,7,3,2,8,5,1,4,5,8,5,6,7,5,7,3,3,9,6,8,1,5,1,1,1,0,3
//        };
//        int k = 500;

//        System.out.println("ones len " + ones.length + ", twos len " + twos.length);

        int[] res = new MaxConcatDigitsMerge().maxNumber(ones, twos, k);
        for (int x : res) {
            System.out.print(x + ",");
        }

        System.out.println();

//        int[] res2 = new MaxConcatDigits().maxNumber2(ones, twos, k);
//        for (int x : res2) {
//            System.out.print(x + ",");
//        }
//
//        System.out.println();
//        for (int i = 0; i < k; i++) {
//            if (res[i] != res2[i]) {
//                System.out.println("diff!");
//                break;
//            }
//        }
    }
}
