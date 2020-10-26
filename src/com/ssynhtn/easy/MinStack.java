package com.ssynhtn.easy;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class MinStack {

    PriorityQueue<Integer> heap;
    ArrayDeque<Integer> stack;

    /** initialize your data structure here. */
    public MinStack() {
        heap = new PriorityQueue<>();
        stack = new ArrayDeque<>();
    }
    
    public void push(int x) {
        stack.push(x);
        heap.add(x);
    }
    
    public void pop() {
        if (stack.isEmpty()) return;
        int x = stack.pop();
        heap.remove(x);
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return heap.peek();
    }
}