package com.ssynhtn.medium;

public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int[] nums = new int[n + 1];
        nums[0] = 1;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int x = 0; x < i / 2; x++) {
                sum += nums[x] * nums[i - 1 - x] * 2;
                if (i == n) {
                    System.out.println(nums[x] + " * " + nums[i - 1 - x] + " * 2");
                }
            }
            if ((i & 1) == 1) {
                int x = (i >> 1);
                sum += nums[x] * nums[x];

                if (i == n) {
                    System.out.println(nums[x] + " * " + nums[x]);
                }
            }
            nums[i] = sum;
        }
        for (int i = 0; i <= n; i++) {
            System.out.println(nums[i]);
        }
        return nums[n];
    }

    public static void main(String[] args) {
        System.out.println(new UniqueBinarySearchTrees().numTrees(5));
    }

}
