package com.ssynhtn.mock;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        List<Integer> prefix = new ArrayList<>();
        collect(candidates, prefix, 0, candidates.length - 1, target, res);
        return res;
    }

    // partial <= target, index >= 0
    private void collect(int[] candidates, List<Integer> prefix, int partial, int index, int target, List<List<Integer>> res) {
        if (partial == target) {
            res.add(new ArrayList<>(prefix));
            return;
        }

        if (partial + candidates[index] <= target) {
            // do take num at index
            prefix.add(candidates[index]);
            collect(candidates, prefix, partial + candidates[index], index, target, res);
            prefix.remove(prefix.size() - 1);
        }

        // do not take num at index
        if (index > 0) {
            collect(candidates, prefix, partial, index - 1, target, res);
        }

    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(new int[]{2,3,5}, 8));
    }
}
