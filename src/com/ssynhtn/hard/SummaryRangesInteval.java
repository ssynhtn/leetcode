package com.ssynhtn.hard;

import java.util.*;

class SummaryRangesInteval {

    // start -> end
    TreeMap<Integer, int[]> map = new TreeMap<>();

    /** Initialize your data structure here. */
    public SummaryRangesInteval() {

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
        Map.Entry<Integer, int[]> floor = map.floorEntry(val);

        if (floor != null && floor.getValue()[1] >= val) {
            return;
        }

        Map.Entry<Integer, int[]> ceiling = map.ceilingEntry(val);
        if (floor == null || val > floor.getValue()[1] + 1) {
            if (ceiling == null || val < ceiling.getKey() - 1) {
                map.put(val, new int[]{val, val});
                return;
            }

            if (val == ceiling.getKey() - 1) {
                int[] interval = map.remove(ceiling.getKey());
                interval[0] = val;
                map.put(val, interval);
            }

        } else {
            if (ceiling == null || ceiling.getKey() > val + 1) {
                floor.getValue()[1] = val;
            } else {
                floor.getValue()[1] = map.remove(ceiling.getKey())[1];
            }

        }



    }
    
    public int[][] getIntervals() {
        int[][] res = new int[map.size()][2];
        int i = 0;

        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            res[i++] = entry.getValue();
        }

        return res;
    }

    public static void main(String[] args) {
        SummaryRangesInteval ranges = new SummaryRangesInteval();
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