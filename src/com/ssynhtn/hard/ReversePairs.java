package com.ssynhtn.hard;

import javax.swing.plaf.IconUIResource;
import java.util.*;

public class ReversePairs {
    public int reversePairsBrute(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int a = nums[i];
            int a2 = a * 2;
            if (a > 0 && a2 < 0) {
                count += nums.length - i - 1;
            } else if (a < 0 && a2 > 0) {
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {
                if (a2 > nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;

        List<Integer> singles = new ArrayList<>();
        int count = 0;
        int i = nums.length - 1;
        singles.add(nums[i]);
        i--;

        while (i >= 0) {
            int origX = nums[i];
            int x = ((origX & 1) == 0) ? origX / 2 : (origX != Integer.MAX_VALUE ? (origX + 1) / 2 : Integer.MAX_VALUE / 2 + 1);
            int index = Collections.binarySearch(singles, x);
            if (index >= 0) {
                while (index >= 0 && singles.get(index) == x) {
                    index--;
                }
                count = count + (index + 1);
            } else {
                index = -1 - index;
                count = count + index;
            }
            i--;


            index = Collections.binarySearch(singles, origX);
            if (index < 0) index = -1 - index;
            singles.add(index, origX);
        }


        return count;

    }

    public int reversePairsMerge(int[] nums) {
        int[] buffer = new int[nums.length / 2 + 1];

        return reversePairsMerge(nums, buffer, 0, nums.length - 1);

    }

    private int reversePairsMerge(int[] nums, int[] buffer, int left, int right) {
        if (right <= left) return 0;

        int count = 0;
        int mid = (left + right) / 2;
        int leftPlus = reversePairsMerge(nums, buffer, left, mid);
//        System.out.println("leftPlus from " + left + " to " + mid + " is " + leftPlus);
        int rightPlus = reversePairsMerge(nums, buffer, mid+1, right);
//        System.out.println("rightPlus from " + (mid + 1) + " to " + right + " is " + rightPlus);
        count += leftPlus + rightPlus;

        int i = left;
        int j = mid + 1;
        int add = 0;
//        for (int index = left; index <= right; index++) {
//            System.out.print(nums[index] + ",");
//        }
//        System.out.println();
        while (i <= mid) {
            int origX = nums[i];
            int x = ((origX & 1) == 0) ? origX / 2 : (origX != Integer.MAX_VALUE ? (origX + 1) / 2 : Integer.MAX_VALUE / 2 + 1);
            while (j <= right && x > nums[j]) {
                j++;
            }
            add += j - mid - 1;


            i++;
        }
//        System.out.println(left + " to " + mid + " compare to " + (mid + 1) + " to " + right + " has " + add + " pairs");
        count += add;

        // merge
        System.arraycopy(nums, left, buffer, 0, mid - left + 1);
        i = 0;
        int bright = mid - left;
        j = mid + 1;
        int k = left;
        while (i <= bright && j <= right) {
            if (buffer[i] < nums[j]) {
                nums[k] = buffer[i];
                k++;
                i++;
            } else {
                nums[k] = nums[j];
                k++;
                j++;
            }
        }

        while (i <= bright) {
            nums[k] = buffer[i];
            k++;
            i++;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new ReversePairs().reversePairsMerge(new int[]{1,3,2,3,1}));
    }
}
