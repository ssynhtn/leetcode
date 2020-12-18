package com.ssynhtn.hard;

import java.util.Map;
import java.util.TreeMap;

class SummaryRanges {

    // start -> end
    TreeMap<Integer, Integer> map = new TreeMap<>();

    /** Initialize your data structure here. */
    public SummaryRanges() {

    }

    /**
     *
     get floor:
     if found and val inside, do nothing
     not found
     get ceiling
     if not found, add
     if inside, do nothing
     if == -1, remove, add

     val = end + 1
     get ceiling
     not found: remove, add
     if == -1, remove both, add

     val > end + 1
     get ceiling
     not found: add
     if inside : do nothing
     if == -1 : remove, add

     */
    public void addNum(int val) {
        Map.Entry<Integer, Integer> floor = map.floorEntry(val);

        if (floor != null && floor.getValue() >= val) {
            return;
        }

        Map.Entry<Integer, Integer> ceiling = map.ceilingEntry(val);
        if (floor == null || val > floor.getValue() + 1) {
            if (ceiling == null || val < ceiling.getKey() - 1) {
                map.put(val, val);
                return;
            }

            if (val == ceiling.getKey() - 1) {
                map.put(val, map.remove(ceiling.getKey()));
            }

        } else {
            if (ceiling == null || ceiling.getKey() > val + 1) {
                map.put(floor.getKey(), val);
            } else {
                map.put(floor.getKey(), map.remove(ceiling.getKey()));
            }

        }



    }
    
    public int[][] getIntervals() {
        int[][] res = new int[map.size()][2];
        int i = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res[i][0] = entry.getKey();
            res[i][1] = entry.getValue();
            i++;
        }

        return res;
    }

    public static void main(String[] args) {
        SummaryRanges ranges = new SummaryRanges();
        int[] values = {1, 3,
                7, 2, 6,
        };
        for (int val : values) {
            ranges.addNum(val);
//            System.out.println("map " + ranges.map);
            int[][] res = ranges.getIntervals();
            for (int[] interval : res) {
                System.out.print(interval[0] + "-" + interval[1] + ", ");
            }
            System.out.println();
        }
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */