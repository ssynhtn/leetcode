package com.ssynhtn.easy;

import java.util.*;

public class MinStack2 {


    List<Integer> data;
    int min = Integer.MAX_VALUE;

    /** initialize your data structure here. */
    public MinStack2() {
        data = new ArrayList<>();
    }
    
    public void push(int x) {
        data.add(x);
        min = Math.min(min, x);
    }
    
    public void pop() {
        int x = data.remove(data.size() - 1);
        if (x == min) {
            min = Integer.MAX_VALUE;
            for (int y : data) {
                if (y < min) {
                    min = y;
                }
            }
        }
    }
    
    public int top() {
        return data.get(data.size() - 1);
    }
    
    public int getMin() {
        return min;
    }
}