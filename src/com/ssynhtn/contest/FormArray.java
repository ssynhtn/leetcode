package com.ssynhtn.contest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FormArray {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Set<int[]> tokens = new HashSet<>();
        for (int[] p : pieces) {
            tokens.add(p);
        }

        int i = 0;
        int n = arr.length;
        while (i < n) {
            boolean found = false;
            Iterator<int[]> it = tokens.iterator();
            while (it.hasNext()){
                int[] p = it.next();
                if (match(arr, i, p)) {
                    found = true;
                    i += p.length;
                    it.remove();
                    break;
                }
            }

            if (!found) return false;
        }

        return true;
    }

    private boolean match(int[] arr, int i, int[] p) {
        int j = 0;
        while (j < p.length && arr[i] == p[j]) {
            i++;
            j++;
        }

        return j == p.length;
    }

    public static void main(String[] args) {
        System.out.println(new FormArray().canFormArray(new int[]{15, 88}, new int[][]{{88}, {15}}));
    }
}
