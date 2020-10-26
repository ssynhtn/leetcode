package com.ssynhtn.easy;

import java.util.ArrayList;
import java.util.List;

public class MinStack3 {

    List<Integer> data;
    List<Integer> minData;

    /** initialize your data structure here. */
    public MinStack3() {
        data = new ArrayList<>();
        minData = new ArrayList<>();
    }
    
    public void push(int x) {
        data.add(x);

        if (minData.isEmpty()) {
            minData.add(x);
        } else {
            minData.add(Math.min(x, minData.get(minData.size() - 1)));
        }
    }
    
    public void pop() {
        data.remove(data.size() - 1);
        minData.remove(minData.size() - 1);
    }
    
    public int top() {
        return data.get(data.size() - 1);
    }
    
    public int getMin() {
        return minData.get(minData.size() - 1);
    }
}