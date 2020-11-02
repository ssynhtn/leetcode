package com.ssynhtn.contest;

import java.util.PriorityQueue;

public class LadderBrick {

//    keep <=k largest diffs, and s: sum of others and currentIndex
//
//    next height: h
//    diff.size < k, add h to diffs
//    l = smallest largest diff, if l + s > N, then we can't
//    replace l with h, add l to s, currentIndex++
//
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int i = 0;

        PriorityQueue<Integer> diffs = new PriorityQueue<>();
        int s = 0;
        int n = heights.length;

        while (i < n - 1) {
            int h = heights[i+1] - heights[i];
            if (h <= 0) {
                i++;
                continue;
            }

            if (diffs.size() < ladders) {
                diffs.add(h);
                i++;
                continue;
            }

            if (ladders > 0) {
                int l = diffs.remove();
                int d = Math.min(l, h);
                if (d + s > bricks) {
                    break;
                }

                s = s + d;
                diffs.add(Math.max(l, d));
                i++;
            } else {
                if (h + s > bricks) {
                    break;
                }
                s = s + h;
                i++;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        System.out.println(new LadderBrick().furthestBuilding(new int[]{14,3,19,3}, 17, 1));
    }
}
