package com.ssynhtn.medium;

import com.sun.org.apache.bcel.internal.generic.FSUB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> collect = new ArrayList<>();

        Arrays.sort(nums);
        List<Integer> prefix = new ArrayList<>();

        recurse(nums, 0, prefix, collect);
        return collect;
    }

    private void recurse(int[] nums, int pos, List<Integer> prefix, List<List<Integer>> collect) {
        if (pos == nums.length) {
            collect.add(new ArrayList<>(prefix));
            return;
        }

        int x = nums[pos];
        int j = pos;
        while (j < nums.length && nums[j] == x) {
            j++;
        }
        int count = j - pos;
        recurse(nums, j, prefix, collect);
        for (int c = 0; c < count; c++) {
            prefix.add(x);
            recurse(nums, j, prefix, collect);
        }
        for (int c = 0; c < count; c++) {
            prefix.remove(prefix.size() - 1);
        }

    }

    public static void main(String[] args) {
        System.out.println(new SubsetsII().subsetsWithDup(new int[]{1, 2, 2}));
    }
}
