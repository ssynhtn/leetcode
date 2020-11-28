package com.ssynhtn.hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.TreeMap;

class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) return nums;

        ArrayDeque<Integer> maxes = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];

        for (int i = 0; i < k; i++) {
            while (!maxes.isEmpty() && maxes.peekLast() < nums[i]) {
                maxes.removeLast();
            }

            maxes.addLast(nums[i]);
        }

        int j = 0;
        int i = k;
        res[0] = maxes.peekFirst();

        while (i < n) {
            if (maxes.peekFirst() == nums[j]) {
                maxes.removeFirst();
            }

            while (!maxes.isEmpty() && maxes.peekLast() < nums[i]) {
                maxes.removeLast();
            }

            maxes.addLast(nums[i]);
            res[++j] = maxes.peekFirst();
            i++;
        }

        return res;
    }

    public int[] maxSlidingWindowByIndex(int[] nums, int k) {
        if (k == 1) return nums;

        ArrayDeque<Integer> largeIndexes = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];

        for (int i = 0; i < k; i++) {
            while (!largeIndexes.isEmpty() && nums[largeIndexes.peekLast()] <= nums[i]) {
                largeIndexes.removeLast();
            }

            largeIndexes.addLast(i);
        }

        int j = 0;
        int i = k;
        res[0] = nums[largeIndexes.peekFirst()];

        while (i < n) {
            if (largeIndexes.peekFirst() == j) {
                largeIndexes.removeFirst();
            }

            while (!largeIndexes.isEmpty() && nums[largeIndexes.peekLast()] <= nums[i]) {
                largeIndexes.removeLast();
            }

            largeIndexes.addLast(i);
            res[++j] = nums[largeIndexes.peekFirst()];
            i++;
        }

        return res;
    }

    public int[] maxSlidingWindowBrute(int[] nums, int k) {
        if (k == 1) return nums;

        int n = nums.length;
        int[] res = new int[n - k + 1];
        TreeMap<Integer, Integer> counts = new TreeMap<>();
        for (int i = 0; i < k; i++) {
            counts.merge(nums[i], 1, Integer::sum);
        }

        res[0] = counts.lastKey();
        int j = 0;
        int i = k;
        while (i < n) {
            counts.merge(nums[i++], 1, Integer::sum);
            counts.merge(nums[j], -1, Integer::sum);
            if (counts.get(nums[j]) == 0) {
                counts.remove(nums[j]);
            }

            res[++j] = counts.lastKey();
        }

        return res;

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MaxSlidingWindow().maxSlidingWindowByIndex(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
        System.out.println(Arrays.toString(new MaxSlidingWindow().maxSlidingWindowBrute(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
    }
}