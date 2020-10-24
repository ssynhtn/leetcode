package com.ssynhtn.mock;


import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargest3 {

    int[] buffer;   // smallest at 0
    int size;
    public KthLargest3(int k, int[] nums) {
        buffer = new int[k];

        for (int x : nums) {
            add(x);
        }
    }
    
    public int add(int val) {
        if (size < buffer.length) {
            buffer[size++] = val;

            if (size == buffer.length) {
                Arrays.sort(buffer);
            }

            return buffer[0];
        }

        if (val <= buffer[0]) {
            return buffer[0];
        }

        buffer[0] = val;
        Arrays.sort(buffer);
        return buffer[0];
    }

    public static void main(String[] args) {
        KthLargest3 kthLargest = new KthLargest3(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }

}