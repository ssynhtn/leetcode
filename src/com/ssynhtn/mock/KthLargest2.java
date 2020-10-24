package com.ssynhtn.mock;


import java.util.PriorityQueue;

public class KthLargest2 {

    PriorityQueue<Integer> heap;
    int k;

    public KthLargest2(int k, int[] nums) {
        this.k = k;
        heap = new PriorityQueue<>();

        for (int x : nums) {
            add(x);
        }
    }
    
    public int add(int val) {
        if (heap.size() < k) {
            heap.add(val);
        } else {
            if (val > heap.peek()) {
                heap.poll();
                heap.add(val);
            }
        }

        return heap.peek();
    }

    public static void main(String[] args) {
        KthLargest2 kthLargest = new KthLargest2(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }

}