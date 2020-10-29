package com.ssynhtn.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortedRange {
    public List<String> summaryRangesBinary(int[] nums) {
        List<Integer> ranges = computeRange(nums, 0, nums.length);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < ranges.size(); i+=2) {
            int a = ranges.get(i);
            int b = ranges.get(i + 1);
            if (a == b) {
                res.add(a + "");
            } else {
                res.add(a + "->" + b);
            }
        }
        return res;
    }

    // start <= end
    private List<Integer> computeRange(int[] nums, int start, int end) {
        if (end == start) return Collections.emptyList();
        if (end == start + 1) {
            List<Integer> res = new ArrayList<>();
            res.add(nums[start]);
            res.add(nums[start]);
            return res;
        }
        int mid = start + (end - start) / 2;
        List<Integer> a = computeRange(nums, start, mid);
        List<Integer> b = computeRange(nums, mid, end);
        int i = 0;
        if (b.get(0) == a.get(a.size() - 1) + 1) {
            a.set(a.size() - 1, b.get(1));
            i = 2;
        }
        while (i < b.size()) {
            a.add(b.get(i++));
        }

        return a;
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        int i = 0;

        StringBuilder sb = new StringBuilder();
        while (i < n) {
            int j = i;
            while (j < n - 1 && nums[j+1] == nums[j] + 1) {
                j++;
            }
            if (j == i) {
                res.add(Integer.toString(nums[i]));
            } else {
                sb.append(nums[i]).append("->").append(nums[j]);
                res.add(sb.toString());
                sb.delete(0, sb.length());
//                res.add(nums[i] + "->" + nums[j]);
            }
            i = j + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SortedRange().summaryRangesBinary(new int[]{0,1,2,4,5,7}));
    }
}
