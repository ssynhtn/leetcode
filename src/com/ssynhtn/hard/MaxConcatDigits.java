package com.ssynhtn.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxConcatDigits {
    Map<Long, int[]> cache = new HashMap<>();
    private int[] findMaxNum(int[] nums1, int[] nums2, int[][] nextIndex1, int[][] nextIndex2, int l1, int r1, int l2, int r2, int k) {
        long kLong = k;
        long l1Long = l1;
        long l2Long = l2;
        long key = (kLong << 40) | (l1Long << 20) | l2Long;
        int[] c = cache.get(key);
        if (c != null) return c;
        int[] out = new int[k];
        int index = 0;
        while (k > 0) {
            int d1 = -1, next1 = -1;

            if (r1 > l1) {
                for (d1 = 9; d1 >= 0; d1--) {
                    next1 = nextIndex1[l1][d1];

                    if (next1 >= l1 && next1 < r1 && next1 <= r1 + r2 - l2 - k) {
                        break;
                    }
                }
            }

            int d2 = -1, next2 = -1;
            if (r2 > l2) {
                for (d2 = 9; d2 >= 0; d2--) {
                    next2 = nextIndex2[l2][d2];

                    if (next2 >= l2 && next2 < r2 && next2 <= r2 + r1 - l1 - k) {
                        break;
                    }
                }
            }

            if (d1 > d2) {
                out[index++] = d1;
                k--;
                l1 = next1 + 1;
            } else if (d2 > d1) {
                out[index++] = d2;
                k--;
                l2 = next2 + 1;
            } else {
                // d1 == d2
                out[index++] = d1;
                k--;
                // 使用next1
//                System.out.println("same d " + d1 + " at " + next1 + " and " + next2);
                int[] first = findMaxNum(nums1, nums2, nextIndex1, nextIndex2,  next1 + 1, r1, l2, r2, k);
//                System.out.println("rec for first arr is ");
//                for (int j = index; j < index + k; j++) {
//                    System.out.print(out[j] + ",");
//                }
//                System.out.println();
                int[] second = findMaxNum(nums1, nums2, nextIndex1, nextIndex2, l1, r1, next2 + 1, r2, k);
//                System.out.println("rec for second arr is ");
//                for (int j = 0; j < k; j++) {
//                    System.out.print(buff[j] + ",");
//                }
//                System.out.println();

                int end = k;
                int i = 0;
                while (i < end && first[i] == second[i]) {
                    i++;
                }

                if (i < end && first[i] < second[i]) {
                    System.arraycopy(second, 0, out, index, k);
                } else {
                    System.arraycopy(first, 0, out, index, k);
                }

                break;
            }

        }

        cache.put(key, out);
        return out;

    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
//        System.out.println("nums1 " + Arrays.toString(nums1));
//        System.out.println("nums2 " + Arrays.toString(nums2));
        int n = nums1.length;
        int m = nums2.length;

        int[][] nextIndex1 = buildNextIndex(nums1, n);
        int[][] nextIndex2 = buildNextIndex(nums2, m);

        return findMaxNum(nums1, nums2, nextIndex1, nextIndex2, 0, n, 0, m, k);

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

    public static void main(String[] args) {
//        int k = 5;
//        int[] ones = new int[]{
//                2,4,
//        };
//        int[] twos = new int[]{
//                2,5,2,
//        };
//
        int k = 80;
        int[] ones = new int[]{
                1,5,8,1,4,0,8,5,0,7,0,5,7,6,0,5,5,2,4,3,6,4,6,6,3,8,1,1,3,1,3,5,4,3,9,5,0,3,8,1,4,9,8,8,3,4,6,2,5,4,1,1,4,6,5,2,3,6,3,5,4,3,0,7,2,5,1,5,3,3,8,2,2,7,6,7,5,9,1,2
        };
        int[] twos = new int[]{
                7,8,5,8,0,1,1,6,1,7,6,9,6,6,0,8,5,8,6,3,4,0,4,6,7,8,7,7,7,5,7,2,5,2,1,9,5,9,3,7,3,9,9,3,1,4,3,3,9,7,1,4,4,1,4,0,2,3,1,3,2,0,2,4,0,9,2,0,1,3,9,1,2,2,6,6,9,3,6,0
        };

//        int[] ones = new int[]{
//                8,9,7,3,5,9,1,0,8,5,3,0,9,2,7,4,8,9,8,1,0,2,0,2,7,2,3,5,4,7,4,1,4,0,1,4,2,1,3,1,5,3,9,3,9,0,1,7,0,6,1,8,5,6,6,5,0,4,7,2,9,2,2,7,6,2,9,2,3,5,7,4,7,0,1,8,3,6,6,3,0,8,5,3,0,3,7,3,0,9,8,5,1,9,5,0,7,9,6,8,5,1,9,6,5,8,2,3,7,1,0,1,4,3,4,4,2,4,0,8,4,6,5,5,7,6,9,0,8,4,6,1,6,7,2,0,1,1,8,2,6,4,0,5,5,2,6,1,6,4,7,1,7,2,2,9,8,9,1,0,5,5,9,7,7,8,8,3,3,8,9,3,7,5,3,6,1,0,1,0,9,3,7,8,4,0,3,5,8,1,0,5,7,2,8,4,9,5,6,8,1,1,8,7,3,2,3,4,8,7,9,9,7,8,5,2,2,7,1,9,1,5,5,1,3,5,9,0,5,2,9,4,2,8,7,3,9,4,7,4,8,7,5,0,9,9,7,9,3,8,0,9,5,3,0,0,3,0,4,9,0,9,1,6,0,2,0,5,2,2,6,0,0,9,6,3,4,1,2,0,8,3,6,6,9,0,2,1,6,9,2,4,9,0,8,3,9,0,5,4,5,4,6,1,2,5,2,2,1,7,3,8,1,1,6,8,8,1,8,5,6,1,3,0,1,3,5,6,5,0,6,4,2,8,6,0,3,7,9,5,5,9,8,0,4,8,6,0,8,6,6,1,6,2,7,1,0,2,2,4,0,0,0,4,6,5,5,4,0,1,5,8,3,2,0,9,7,6,2,6,9,9,9,7,1,4,6,2,8,2,5,3,4,5,2,4,4,4,7,2,2,5,3,2,8,2,2,4,9,8,0,9,8,7,6,2,6,7,5,4,7,5,1,0,5,7,8,7,7,8,9,7,0,3,7,7,4,7,2,0,4,1,1,9,1,7,5,0,5,6,6,1,0,6,9,4,2,8,0,5,1,9,8,4,0,3,1,2,4,2,1,8,9,5,9,6,5,3,1,8,9,0,9,8,3,0,9,4,1,1,6,0,5,9,0,8,3,7,8,5
//        };
//        int[] twos = new int[]{
//                7,8,4,1,9,4,2,6,5,2,1,2,8,9,3,9,9,5,4,4,2,9,2,0,5,9,4,2,1,7,2,5,1,2,0,0,5,3,1,1,7,2,3,3,2,8,2,0,1,4,5,1,0,0,7,7,9,6,3,8,0,1,5,8,3,2,3,6,4,2,6,3,6,7,6,6,9,5,4,3,2,7,6,3,1,8,7,5,7,8,1,6,0,7,3,0,4,4,4,9,6,3,1,0,3,7,3,6,1,0,0,2,5,7,2,9,6,6,2,6,8,1,9,7,8,8,9,5,1,1,4,2,0,1,3,6,7,8,7,0,5,6,0,1,7,9,6,4,8,6,7,0,2,3,2,7,6,0,5,0,9,0,3,3,8,5,0,9,3,8,0,1,3,1,8,1,8,1,1,7,5,7,4,1,0,0,0,8,9,5,7,8,9,2,8,3,0,3,4,9,8,1,7,2,3,8,3,5,3,1,4,7,7,5,4,9,2,6,2,6,4,0,0,2,8,3,3,0,9,1,6,8,3,1,7,0,7,1,5,8,3,2,5,1,1,0,3,1,4,6,3,6,2,8,6,7,2,9,5,9,1,6,0,5,4,8,6,6,9,4,0,5,8,7,0,8,9,7,3,9,0,1,0,6,2,7,3,3,2,3,3,6,3,0,8,0,0,5,2,1,0,7,5,0,3,2,6,0,5,4,9,6,7,1,0,4,0,9,6,8,3,1,2,5,0,1,0,6,8,6,6,8,8,2,4,5,0,0,8,0,5,6,2,2,5,6,3,7,7,8,4,8,4,8,9,1,6,8,9,9,0,4,0,5,5,4,9,6,7,7,9,0,5,0,9,2,5,2,9,8,9,7,6,8,6,9,2,9,1,6,0,2,7,4,4,5,3,4,5,5,5,0,8,1,3,8,3,0,8,5,7,6,8,7,8,9,7,0,8,4,0,7,0,9,5,8,2,0,8,7,0,3,1,8,1,7,1,6,9,7,9,7,2,6,3,0,5,3,6,0,5,9,3,9,1,1,0,0,8,1,4,3,0,4,3,7,7,7,4,6,4,0,0,5,7,3,2,8,5,1,4,5,8,5,6,7,5,7,3,3,9,6,8,1,5,1,1,1,0,3
//        };
//        int k = 500;

//        System.out.println("ones len " + ones.length + ", twos len " + twos.length);

        int[] res = new MaxConcatDigits().maxNumber(ones, twos, k);
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
