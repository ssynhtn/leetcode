package com.ssynhtn.hard;

import java.util.Arrays;

public class TallestBillboard {
    int max = 0;
    int sum;
    public int tallestBillboard(int[] rods) {
        Arrays.sort(rods);
        reverse(rods);
        int n = rods.length;
        for (int r : rods) {
            sum += r;
        }

        collect(0, 0, 0, rods, 0, n);
        return max;
    }

    // left >= right
    private void collect(int left, int right, int removed, int[] rods, int i, int n) {

        int bound = (sum - removed) / 2;
        if (bound <= max) return;

        int rightBound = sum - removed - left;
        if (rightBound < left) return;

        if (rightBound == left) {
            max = rightBound;
            return;
        }

        if (i >= n) return;
        // case 1 skip
        collect(left, right, removed + rods[i], rods, i + 1, n);
        // case 2, add to left
        collect(left + rods[i], right, removed, rods, i + 1, n);
        // case 3, add to right
        int newLeft = left;
        int newRight = right + rods[i];
        if (newRight > newLeft) {
            newLeft = newRight;
            newRight = left;
        }

        collect(newLeft, newRight, removed, rods, i + 1, n);
    }

    private void reverse(int[] rods) {
        int i = 0;
        int j = rods.length - 1;
        while (i < j) {
            int temp = rods[i];
            rods[i] = rods[j];
            rods[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        System.out.println(new TallestBillboard().tallestBillboard(new int[]{
                58,66,60,59,53,66,63,55,49,74,60,71,47,59,58,63,66,65,61,47
        }));

    }
}