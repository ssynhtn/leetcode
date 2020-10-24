package com.ssynhtn.mock;


public class CanJump {
    public boolean canJump(int[] nums) {
        int minIndex = nums.length - 1;
        int i = minIndex - 1;
        while (i >= 0) {
            if (i + nums[i] >= minIndex) {
                minIndex = i;
            }
            i--;
        }

        return minIndex == 0;
    }

    public static void main(String[] args) {
        System.out.println(new CanJump().canJump(new int[]{3,2,1,0,4}));
    }



}
