package com.ssynhtn.contest;

import java.util.*;

public class FreqSort {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int x : nums) {
            counts.merge(x, 1, Integer::sum);
        }

        List<int[]> list = new ArrayList<>();
        for (int x : counts.keySet()) {
            list.add(new int[]{x, counts.get(x)});
        }

        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o2[0] - o1[0];
                }
                return o1[1] - o2[1];
            }
        });

        int i = 0;
        for (int[] p : list) {
            for (int j = 0; j < p[1]; j++) {
                nums[i++] = p[0];
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FreqSort().frequencySort(new int[]{
                -1,1,-6,4,5,-6,1,4,1
        })));
    }
}
