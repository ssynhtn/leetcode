package com.ssynhtn.mock;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Collections;
import java.util.LongSummaryStatistics;
import java.util.PriorityQueue;

public class LongestSubArray {
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        if (n <= 1) return n;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int maxLen = 0;
        int start = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (minHeap.size() > 0 && (Math.abs(x - minHeap.peek()) > limit || Math.abs(x - maxHeap.peek()) > limit)) {
                minHeap.remove(nums[start]);
                maxHeap.remove(nums[start]);
                start++;
            }

            minHeap.add(x);
            maxHeap.add(x);
            maxLen = Math.max(maxLen, minHeap.size());
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubArray().longestSubarray(new int[]{4,2,2,2,4,4,2,2}, 0));
    }
}
