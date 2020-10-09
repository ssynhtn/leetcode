package com.ssynhtn.easy;

import java.util.Arrays;

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int k;
        int s = m + n;
        while (i < s) {
            while (i - j < m && j < n && nums1[i] < nums2[j]) {
                i++;
            }

            if (i - j == m) {
                while (j < n) {
                    nums1[i++] = nums2[j++];
                }
                break;
            } else if (j == n) {
                break;
            }

            k = j + 1;
            while (k < n && nums2[k] <= nums1[i]) {
                k++;
            }

            int count = k - j;
            System.arraycopy(nums1, i, nums1, i + count, m + j - i);
            System.arraycopy(nums2, j, nums1, i, count);
            j = k;
            i = i + count;
        }
    }

    public void mergeBySort(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1, 0, m + n);

    }

    public void mergeFromEnd(int[] nums1, int m, int[] nums2, int n) {
        int pos = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while (j >= 0 && i >= 0) {
            if (nums2[j] > nums1[i]) {
                System.out.println("moving " + nums2[j] + " to " + pos);
                nums1[pos--] = nums2[j--];
            } else {
                System.out.println("moving " + nums1[i] + " to " + pos);
                nums1[pos--] = nums1[i--];
            }
        }

        if (j >= 0) {
            System.arraycopy(nums2, 0, nums1, 0, j + 1);
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {0};
        int[] nums2 = {1};
        new MergeSortedArray().mergeFromEnd(nums1, 0, nums2, 1);
        System.out.println(Arrays.toString(nums1));
    }
}
