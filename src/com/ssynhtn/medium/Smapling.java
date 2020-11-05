package com.ssynhtn.medium;

class Smapling {
    int[] nums;

    public Smapling(int[] nums) {
        this.nums = nums;
    }
    
    public int pick(int target) {
        int n = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (x == target) {
                n++;
                if (Math.random() * n < 1) {
                    res = i;
                }
            }
        }
        
        return res;
    }
}