package com.ssynhtn.medium;

public class SearchInRotatedSortedArrayII {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid]) return mid;
            if (nums[mid] > nums[left] || nums[mid] > nums[right]) {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[left] || nums[mid] < nums[right]){
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                int i = mid + 1;
                while (i <= right && nums[i] == nums[mid]) {
                    i++;
                }
                if (i > right) {
                    right = mid - 1;
                } else {
                    left = i;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new SearchInRotatedSortedArrayII().search(new int[]{0, 1, 0, 0, 0}, 1));
    }
}
