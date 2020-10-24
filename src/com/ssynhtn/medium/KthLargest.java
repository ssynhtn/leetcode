package com.ssynhtn.medium;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargest {

    public int findKthLargestHeap(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int val : nums) {
            if (heap.size() < k) {
                heap.add(val);
            } else {
                if (val > heap.peek()) {
                    heap.poll();
                    heap.add(val);
                }
            }
        }

        return heap.peek();
    }


    public int findKthLargest(int[] nums, int k) {
        return sort(nums, 0, nums.length - 1, nums.length - k);
    }

    // quick sort 很难搞
    private int sort(int[] nums, int l, int r, int k) {
        if (l == r) return nums[l];

        swap(nums, l, (l + r) / 2);

        int x = nums[l];
        int i = l;
        int j = r;


        // l until i, r until j, [i, j] is unchecked
        while (i <= j) {
            while (i <= j && nums[i] <= x) {
                i++;
            }

            while (j >= i && nums[j] > x) {
                j--;
            }

            //  i != j
            if (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

//        System.out.println("after partition of " + l + " to " + r + ", " + Arrays.toString(nums));
//        System.out.println("j " + j + ", i " + i);

        // j + 1 = i, [l, j] small, [i, r] large
        if (k == j - l) {
            return nums[l];
        }

        swap(nums, l, j);



        if (k < j - l) {
            return sort(nums, l, j - 1, k);
        } else {
            return sort(nums, i, r, k - (j - l + 1));
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
//        System.out.println(new KthLargest().findKthLargest(new int[]{3,2,1,5,6,4}, 0));
//        System.out.println(new KthLargest().findKthLargest(new int[]{3,2,1,5,6,4}, 1));
        System.out.println(new KthLargest().findKthLargest(new int[]{3,2,1,5,6,4}, 2));
//        System.out.println(new KthLargest().findKthLargest(new int[]{3,2,1,5,6,4}, 3));
//        System.out.println(new KthLargest().findKthLargest(new int[]{3,2,1,5,6,4}, 4));
//        System.out.println(new KthLargest().findKthLargest(new int[]{3,2,1,5,6,4}, 5));
//        System.out.println(new KthLargest().findKthLargest(new int[]{3,2,1,5,6,4}, 6));
//        System.out.println(new KthLargest().findKthLargest(new int[]{3,2,1,5,6,4}, 7));
    }


}