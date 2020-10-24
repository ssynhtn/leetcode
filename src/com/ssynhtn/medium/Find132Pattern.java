package com.ssynhtn.medium;

import java.util.ArrayList;
import java.util.List;

public class Find132Pattern {
    public boolean find132pattern(int[] nums) {
//        stack of highs
//        stack of lows
//
//                x = 0th // x is the dangling low
//
//                i = 1 until n, let t = ith
//        if t < x, replace x as the dangling low
//        if t == x, continue
//        if t > x
//
//        if stack is empty, or t <= lows.peek, add(x, t) && x = find next dangling low
//        if stack is not empty and t > lows.peek
//        if t >= 0th pairs' high, replace (x, t) as the only pair, && x = find next dangling low
//        if t < 0th high
//        from right to left, find first pair that t < high
//        if t > that low, true
//        replace next and other pairs with (x, t) && x = find next dangling low
//
//


        int n = nums.length;
        if (n < 3) return false;

        int[] highs = new int[n];
        int[] lows = nums;  // reusing nums as lows
        int s = 0;  // size of intervals
        int min = nums[0];
        int i = 1;
        while (i < n) {
            int t = nums[i++];
            if (t <= min) {
                min = t;
                continue;
            }

            // t > min
            int k = s - 1;
            while (k >= 0 && t >= highs[k]) {
                k--;
            }

            if (k >= 0 && t > lows[k]) return true;
            s = k + 1;

            lows[s] = min;
            highs[s] = t;
            s++;

        }


        return false;
    }

    public boolean find132patternList(int[] nums) {
//        stack of highs
//        stack of lows
//
//                x = 0th // x is the dangling low
//
//                i = 1 until n, let t = ith
//        if t < x, replace x as the dangling low
//        if t == x, continue
//        if t > x
//
//        if stack is empty, or t <= lows.peek, add(x, t) && x = find next dangling low
//        if stack is not empty and t > lows.peek
//        if t >= 0th pairs' high, replace (x, t) as the only pair, && x = find next dangling low
//        if t < 0th high
//        from right to left, find first pair that t < high
//        if t > that low, true
//        replace next and other pairs with (x, t) && x = find next dangling low
//
//


        int n = nums.length;
        if (n <3) return false;

        List<Integer> highs = new ArrayList<>();
        List<Integer> lows = new ArrayList<>();
        int x = nums[0];
        int i = 1;
        while (i < n) {
            int t = nums[i++];
            if (t <= x) {
                x = t;
                continue;
            }

            // t > x
            int k = lows.size() - 1;
            while (k >= 0 && t >= highs.get(k)) {
                k--;
            }

            if (k >= 0 && t > lows.get(k)) return true;
            k++;
            while (lows.size() > k) {
                lows.remove(lows.size() - 1);
                highs.remove(highs.size() - 1);
            }

            lows.add(x);
            highs.add(t);

        }


        return false;
    }
    public boolean find132patternN2(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        for (int i = 0; i < n - 2; i++) {
            int x = nums[i];
            int j = i + 1;
            while (j < n && nums[j] <= x) {
                j++;
            }

            if (j >= n - 1) continue;
            int y = nums[j];
            for (int k = j + 1; k < n; k++) {
                int z = nums[k];
                if (z <= x)  continue;
                if (z < y) return true;
                y = z;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Find132Pattern().find132pattern(new int[]{1,2,3,4}));
    }
//    public boolean find132pattern(int[] nums) {
//
//        List<Integer> vallies = new ArrayList<>();
//        List<Integer> peaks = new ArrayList<>();
//
//        int i = 0;
//        int len = nums.length;
//
//        while (i + 1 < len && nums[i + 1] <= nums[i]) {
//            i++;
//        }
//
//        if (i + 1 == len) return false;
//        vallies.add(nums[i]);
//        while (i + 1 < len && nums[i + 1] >= nums[i]) {
//            i++;
//        }
//
//        if (i + 1 == len) return false;
//        peaks.add(nums[i]);
//
//        i++;
//        while (i < len) {
//            int x = nums[i];
//            for (int index = 0; index < vallies.size(); index++) {
//                if (x > vallies.get(index) && x < peaks.get(index)) {
//                    return true;
//                }
//
//                if (x >= peaks.get(index)) {
//
//                }
//            }
//        }
//
//
//        return false;
//    }
}
