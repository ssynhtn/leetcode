package com.ssynhtn.contest;

class StoneGameVII {
    public int stoneGameVII(int[] stones) {
//         diff[i, j], 0<=i<j<=n

// diff[i,i+1] = nums[i]

// diff[i,j] = 
// take i: nums[i] - diff[i+1, j]
// take j-1: nums[j-1] - diff[i, j-1]
        int n = stones.length;
        int[][] diff = new int[n+1][n+1];
        for (int i = 0; i < n; i++) {
            diff[i][i+1] = stones[i];
        }
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len;
                int one = stones[i] - diff[i+1][j];
                int two = stones[j-1] - diff[i][j-1];

                diff[i][j] = Math.min(one, two);
//                if ((n - len) % 2 == 0) {
//                } else {
//                    diff[i][j] = Math.max(one, two);
//                }
            }
        }

        int sum = 0;
        for (int x : stones) {
            sum += x;
        }

        printArray(diff);
        
        return (sum - diff[0][n]) / 2;
    }

    private void printArray(int[][] diff) {

    }

    public static void main(String[] args) {
        System.out.println(new StoneGameVII().stoneGameVII(new int[]{5,3,1,4,2}));
    }
}