package com.ssynhtn.daily;

import java.util.*;

public class ContainsDuplicateIII {

    long minDiff = Long.MAX_VALUE;
    boolean isRemoving;
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length <= 1) return false;
        if (k == 0) return false;

        if (k >= nums.length) {
            k = nums.length - 1;
        }

        Set<Integer> binaryTree = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == o2) return 0;
                if (!isRemoving) {
                    minDiff = Math.min(minDiff, Math.abs(o1.longValue() - o2.longValue()));
//                    System.out.println("minDiff -> " + minDiff + ", isRemoving " + isRemoving + ", o1" + System.identityHashCode(o1) + ", o2" + System.identityHashCode(o2));
                }
                return o1.compareTo(o2);
            }
        });

        int i;
        for (i = 0; i <= k; i++) {
            if (!binaryTree.add(nums[i])) {
                return true;
            }
            if (minDiff <= t) {
//                System.out.println("after " + i + "th element " + nums[i] + ", reached mindiff " + minDiff + ", isRemoving " + isRemoving);
                return true;
            }
        }

        int j = 0;
        while (i < nums.length) {
            isRemoving = true;
            binaryTree.remove(nums[j]);
            isRemoving = false;

            if (!binaryTree.add(nums[i])) {
                return true;
            }
            if (minDiff <= t) {
//                System.out.println("after " + i + "th elem " + nums[i] + ", reached mindiff " +minDiff);
                return true;
            }
            i++;
            j++;
        }

        return false;
    }


    public static void main(String[] args) {
        System.out.println(new ContainsDuplicateIII().containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }
}
