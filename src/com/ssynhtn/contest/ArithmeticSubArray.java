package com.ssynhtn.contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArithmeticSubArray {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        int m = l.length;
        List<Integer> buffer = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int start = l[i];
            int end = r[i];

            buffer.clear();
            for (int j = start; j <= end; j++) {
                buffer.add(nums[j]);
            }
            Collections.sort(buffer);
            int d = buffer.get(1) - buffer.get(0);
            boolean isArithmetic = true;
            for (int k = 2; k < buffer.size(); k++) {
                if (buffer.get(k) - buffer.get(k-1) != d) {
                    isArithmetic = false;
                    break;
                }
            }
            res.add(isArithmetic);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new ArithmeticSubArray().checkArithmeticSubarrays(new int[]{4,6,5,9,3,7}, new int[]{0,0,2}, new int[]{2,3,5}));
    }
}
