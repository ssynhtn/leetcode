package com.ssynhtn.hard;

import java.util.*;

class SummaryRangesList {

    // start -> end
    List<int[]> intervals = new ArrayList<>();

    /** Initialize your data structure here. */
    public SummaryRangesList() {

    }

    public void addNum(int val) {
        if (intervals.isEmpty()) {
            intervals.add(new int[]{val, val});
            return;
        }

        int[] key = new int[]{val, val};
        int index = Collections.binarySearch(intervals, key, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        if (index >= 0) return;
        index = -1 - index;

        if (index == 0) {
            if (intervals.get(0)[0] > val + 1) {
                intervals.add(0, key);
            } else {
                intervals.get(0)[0] = val;
            }

            return;
        }

        int[] left = intervals.get(index - 1);
        if (left[1] >= val) {
            return;
        }

        if (index == intervals.size() || val < intervals.get(index)[0] - 1) {
            if (val == left[1] + 1) {
                left[1]++;
            } else {
                intervals.add(index, key);
            }
        } else {
            if (val == left[1] + 1) {
                left[1] = intervals.get(index)[1];
                intervals.remove(index);
            } else {
                intervals.get(index)[0] = val;
            }
        }


    }
    
    public int[][] getIntervals() {
        return intervals.toArray(new int[intervals.size()][2]);
    }

    public static void main(String[] args) {
        SummaryRangesList ranges = new SummaryRangesList();
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