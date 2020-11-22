package com.ssynhtn.contest;

class FairWay {
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];  // sum 0th to ith
        int[] suffix = new int[n]; // sum right to ith

        int sum = 0;
        for (int i = 0; i < n; i = i + 2) {
            sum += nums[i];
            prefix[i] = sum;
        }
        sum = 0;
        for (int i = 1; i < n; i = i + 2) {
            sum += nums[i];
            prefix[i] = sum;
        }

        sum = 0;
        for (int i = n-1; i >= 0; i -= 2) {
            sum += nums[i];
            suffix[i] = sum;
        }
        sum = 0;
        for (int i = n-2; i >= 0; i -= 2) {
            sum += nums[i];
            suffix[i] = sum;
        }


//        System.out.println(Arrays.toString(nums));
//        System.out.println(Arrays.toString(prefix));
//        System.out.println(Arrays.toString(suffix));
        int count = 0;
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = 0;

            if (i >= 1) {
                left += prefix[i-1];
            }
            if (i + 2 < n) {
                left += suffix[i+2];
            }

            if (i >= 2) {
                right += prefix[i-2];
            }
            if (i + 1 < n) {
                right += suffix[i+1];
            }

            if (left == right) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new FairWay().waysToMakeFair(new int[]{
                1,2,3
        }));
    }
}
