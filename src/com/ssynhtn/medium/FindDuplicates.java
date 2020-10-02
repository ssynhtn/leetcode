package com.ssynhtn.medium;

import java.util.*;

public class FindDuplicates {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> dups = new ArrayList<>();

        printArray(nums);

        int i = 0;
        int n = nums.length;
        while (i < n) {
            int x = nums[i];
            int index = x - 1;
            if (index == i) {
                i++;
                continue;
            }

            if (nums[index] == 0) {
                nums[index] = x;
                nums[i] = 0;
                i++;
                printArray(nums);
            } else if (nums[index] == x) {
                dups.add(x);
                System.out.println("found dup " + x);
                nums[i] = 0;
                i++;
                printArray(nums);
            } else {
                nums[i] = nums[index];
                nums[index] = x;
                printArray(nums);
            }
        }

        return dups;

    }

    private void printArray(int[] nums) {
        for (int n : nums) {
            System.out.print(n + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(new FindDuplicates().findDuplicates(new int[]{4,3,2,7,8,2,3,1}));
    }
}
