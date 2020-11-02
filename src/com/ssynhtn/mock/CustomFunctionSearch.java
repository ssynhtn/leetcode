package com.ssynhtn.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomFunctionSearch {
    interface CustomFunction {
        // Returns f(x, y) for any given positive integers x and y.
        // Note that f(x, y) is increasing with respect to both x and y.
        // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
        public int f(int x, int y);
    };

    public List<List<Integer>> findSolution(CustomFunction f, int z) {
        List<List<Integer>> res = new ArrayList<>();

        final int n = 1000;
        if (f.f(1, 1) > z) return res;
        if (f.f(n , n) < z) return res;

        int x = 1;
        int yMax = n;
        while (x <= n && yMax >= 1) {
            if (f.f(x, 1) > z) break;
            if (f.f(x, n) < z) {
                x++;
                continue;
            }

            int left = 1;
            int right = yMax;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int value = f.f(x, mid);
                if (value == z) {
                    res.add(Arrays.asList(x, mid));
                    x++;
                    yMax = mid - 1;
                    break;
                } else if (value < z) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            // left = right + 1
            // f(x, left) > z

            if (left > right) {
                x++;
                yMax = right;
            }
        }

        return res;
    }
}
