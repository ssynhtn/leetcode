package com.ssynhtn.medium;


public class KthLargestSelect {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, nums.length - k);
    }

    // k index, [left, right] inclusive, left <= right
    private int findKthLargest(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];
        int mid = (left + right) / 2;
        swap(nums, left, mid);
        int pivot = nums[left];
        int i = left + 1;   // < i 都是smaller
        int j = right;      // > j 都是larger

        while (i <= j) {
            while (i <= j && nums[i] <= pivot) {
                i++;
            }
            while (j >= i && nums[j] > pivot) {
                j--;
            }

            if (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        // j = i-1, j at small, i at large
        swap(nums, left, j);
        // 左边 j - left个

        int size = j - left;
        if (k == size) {
            return nums[j];
        }

        if (k < size) {
            return findKthLargest(nums, left, j-1, k);
        } else {
            return findKthLargest(nums, i, right, k - size - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(new KthLargestSelect().findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }
}
