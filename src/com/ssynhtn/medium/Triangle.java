package com.ssynhtn.medium;

import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] buffer = new int[n+1];
        for (int i = n-1; i >= 0; i--) {
            List<Integer> row = triangle.get(i);

            for (int j = 0; j < row.size(); j++) {
                buffer[j] = row.get(j) + Math.min(buffer[j], buffer[j+1]);
            }
        }

        return buffer[0];
        
    }
}