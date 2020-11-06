package com.ssynhtn.hard;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SmallestDistPair {

    public int smallestDistancePair(int[] nums, int k) {
//    n = nums.length
//    sort nums
//    create next index array of size n
//    map: dist(int) -> count
//
//            max = 0;
//for (i = 0 to n -1 - 1)
//    index[i] = i + 1
//    diff = nums[i+1] - nums[i];
//    map[diff]++
//    max = max(max, diff)
//
//if (k <= 1) extract
        int n = nums.length;
        Arrays.sort(nums);
        int[] index = new int[n];
        Map<Integer, Integer> map = new TreeMap<>();
        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            index[i] = i + 1;
            int diff = nums[i+1] - nums[i];
            map.merge(diff, 1, Integer::sum);
            max = Math.max(max, diff);
        }

        if (k <= 2) {
            return extract(map, k);
        }

        //            bound = n - 1
//    step = 2
//    lastIndex = n-3
//    lastMax = max
//    loop until bound >= k
//  for i in 0 to lastIndex
//    i + step <= index[i]  // i+step has been considered skip
//    diff = nums[i+step] - nums[i]
//    map[diff]++
//    update max for diff
//    index[i] = i + step
//    size++
//    j = i + step + 1
//            while (j < n && nums[j] - nums[i] <= lastMax) {
//        index[i] = j
//        j++
//    }
//
//    bound += lastIndex + 1
//    lastMax = max
//    step++
//    lastIndex--
//
//            if k <= n-1
//            if k <= n-1 + n-2


        // todo
        return 0;


    }

    private int extract(Map<Integer, Integer> map, int k) {
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count += entry.getValue();
            if (count >= k) {
                return entry.getKey();
            }
        }

        return -1;
    }
}
