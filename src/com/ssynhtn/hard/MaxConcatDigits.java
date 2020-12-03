package com.ssynhtn.hard;

import java.util.Arrays;

// todo
public class MaxConcatDigits {
    /**
     *
     1, 3, 9, 4, 6, 5
     2, 9, 1, 2, 5, 8, 3

     next[i][d]: 从i开始第一个d的位置
     0 <= i < n, d 0~9
     next[i][9~0]中至少有一个不是-1

     k
     out, index
     l1, r1
     l2, r2

     next[l1][9~0]找到最大的d1, next1 = next[l1][d1] in [l1, r1)
     next[l2][9~0]找到最大的d2, next2 = next[l2][d2] in [l2, r2)

     d1 > d2:
     out[index++] = d1
     k--
     l1 = next[l1][d1]+1
     d2 > d1
     类似
     d2 == d1
     r1 - next1 + r2 - next2 >= origK - index:
     out[index++] = d1
     out[index++] = d1
     k-=2
     l1, l2 update
     else
     k0 = origK - index  - (r1 - next1 + r2 - next2)
     recurse: out, index, l1, next1, l2, next2, k0
     index += k0
     按最大的优先 merge next1, next2
     */
    int[] buffer;
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        int[] out = new int[k];
        buffer = new int[k];

        int[][] nextIndex1 = buildNextIndex(nums1, n);
        int[][] nextIndex2 = buildNextIndex(nums2, m);

        findMaxNum(nums1, nums2, nextIndex1, nextIndex2, out, 0, 0, n, 0, m, k);
        return out;
    }

    private void findMaxNum(int[] nums1, int[] nums2, int[][] nextIndex1, int[][] nextIndex2, int[] out, int index, int l1, int r1, int l2, int r2, int k) {
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
                findMaxNum(nums1, nums2, nextIndex1, nextIndex2, out, index, next1 + 1, r1, l2, r2, k);
                findMaxNum(nums1, nums2, nextIndex1, nextIndex2, buffer, index, l1, r1, next2 + 1, r2, k);

                int end = index + k;
                int i = index;
                while (i < end && out[i] == buffer[i]) {
                    i++;
                }

                if (i < end && out[i] < buffer[i]) {
                    System.arraycopy(buffer, i, out, i, end - i);
                }

                break;
            }

        }


    }

    // [l1, r1)， [l2, r2)中找出更大的k个, 放入out, return winner
    private int fight(int[] nums1, int[] nums2, int[][] nextIndex1, int[][] nextIndex2, int[] out, int index, int l1, int r1, int l2, int r2, int k) {
//        System.out.println("l1 " + l1 + " l2 " + l2 + " r1 " + r1 + " r2 " + r2 + ", index " + index);
        findMaxNum(nums1, nums2, nextIndex1, nextIndex2, out, index, l1, r1, l2, l2, k);
        findMaxNum(nums2, nums1, nextIndex2, nextIndex1, buffer, index, l2, r2, l1, l1, k);

        int i = index;
        int end = index + k;
        while (i < end && out[i] == buffer[i]) {
            i++;
        }

//        System.out.println("i " + i + " end " + end);
        if (i == end) {
//            for (int t = index; t < end; t++) {
//                System.out.println("out " + out[t] + ", buffer " + buffer[t]);
//            }
            return 0;
        }
        if (out[i] > buffer[i]) return 0;
        return 1;
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
        int[] res = new MaxConcatDigits().maxNumber(
                new int[]{1,5,8,1,4,0,8,5,0,7,0,5,7,6,0,5,5,2,4,3,6,4,6,6,3,8,1,1,3,1,3,5,4,3,9,5,0,3,8,1,4,9,8,8,3,4,6,2,5,4,1,1,4,6,5,2,3,6,3,5,4,3,0,7,2,5,1,5,3,3,8,2,2,7,6,7,5,9,1,2},
                new int[]{7,8,5,8,0,1,1,6,1,7,6,9,6,6,0,8,5,8,6,3,4,0,4,6,7,8,7,7,7,5,7,2,5,2,1,9,5,9,3,7,3,9,9,3,1,4,3,3,9,7,1,4,4,1,4,0,2,3,1,3,2,0,2,4,0,9,2,0,1,3,9,1,2,2,6,6,9,3,6,0},
                80
        );
        for (int x : res) {
            System.out.print(x + ",");
        }
    }
}
