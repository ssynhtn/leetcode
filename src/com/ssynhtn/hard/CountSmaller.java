package com.ssynhtn.hard;

import java.util.*;

public class CountSmaller {
    public List<Integer> countSmallerTwo(int[] nums) {
        int[] counts = new int[nums.length];
        int[] buffer = new int[nums.length];

        sort(nums, buffer, counts, 0, nums.length - 1);

//        System.out.println("after sort " + Arrays.toString(nums));
        List<Integer> res = new ArrayList<>();
        for (int c : counts) {
            res.add(c);
        }
        return res;
    }

    private void sort(int[] nums, int[] buffer, int[] counts, int l, int r) {
        if (l >= r) return;

        int m = (l + r) / 2;
        sort(nums, buffer, counts, m + 1, r);
        for (int i = l; i <= m; i++) {
            int index = Arrays.binarySearch(nums, m + 1, r + 1, nums[i]);
            if (index < 0) {
                counts[i] += -1 - index - m - 1;
            } else {
                while (index >= m + 1 && nums[index] == nums[i]) {
                    index--;
                }
                counts[i] += index - m;
            }
        }
        sort(nums, buffer, counts, l, m);
        System.arraycopy(nums, l, buffer, l, m - l + 1);

        int i = l;
        int j = m + 1;
        int k = l;
        while (i <= m && j <= r) {
            if (buffer[i] <= nums[j]) {
                nums[k++] = buffer[i++];
            } else {
                nums[k++] = nums[j++];
            }
        }

        while (i <= m) {
            nums[k++] = buffer[i++];
        }
    }


    // modified merge sort
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new ArrayList<>(nums.length);
        int[][] numbers = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            numbers[i] = new int[]{nums[i], i};
            counts.add(0);
        }
        int[][] buffer = new int[nums.length][2];

        sort(numbers, buffer, counts, 0, nums.length - 1);

//        System.out.println("after sort " + Arrays.toString(nums));
        return counts;
    }

    // 排序的同时将[l, r]区间中左边比右边大的数量加到counts上
    private void sort(int[][] nums, int[][] buffer, List<Integer> counts, int l, int r) {
        if (l >= r) return;

        int m = (l + r) / 2;
        sort(nums, buffer, counts, l, m);
        sort(nums, buffer, counts, m + 1, r);

        System.arraycopy(nums, l, buffer, l, m - l + 1);
        int i = l;
        int j = m + 1;
        int k = l;
//        System.out.println("merging from " + l + " to " + r);
        int size = 0;
        while (i <= m && j <= r) {
            if (buffer[i][0] <= nums[j][0]) {
                counts.set(buffer[i][1], counts.get(buffer[i][1]) + size);
//                System.out.println("count at " + buffer[i][1] + " ++ " + size);
                nums[k++] = buffer[i++];
            } else {
//                System.out.println(buffer[i][0] + " at " + buffer[i][1] + " > " + nums[j][0] + " at " + nums[j][1]);
                size++;
                nums[k++] = nums[j++];
            }
        }

        while (i <= m) {
            counts.set(buffer[i][1], counts.get(buffer[i][1]) + size);
//            System.out.println("count at " + buffer[i][1] + " ++ " + size);
            nums[k++] = buffer[i++];
        }
//        while (j <= r) {
//            nums[k++] = nums[j++];
//        }
    }

    public static void main(String[] args) {
        System.out.println(new CountSmaller().countSmaller(new int[]{1,1}));
    }
}
