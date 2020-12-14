package com.ssynhtn.contest;

import java.util.Arrays;

class DeliverBox {
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        if (n == 0) return 0;
        int[] dp = new int[n + 1];  // dp[i] = min trips to deliver boxes starting from index i

        return compute(boxes, dp, 0, n, maxBoxes, maxWeight);
    }

    private int compute(int[][] boxes, int[] dp, int i, int n, int maxBoxes, int maxWeight) {
        if (i == n) {
            return 0;
        }
        if (dp[i] > 0) return dp[i];

        int w = 0;
        int limit = Math.min(i + maxBoxes, n);
        int j;
        int portIndex = i;
        int portCount = 1;
        for (j = i; j < limit; j++) {
            if (boxes[j][0] != boxes[portIndex][0]) {
                portIndex = j;
                portCount++;
            }
            if (w + boxes[j][1] <= maxWeight) {
                w += boxes[j][1];
            } else {
                break;
            }
        }

//            if (i == 2) {
//                System.out.println("portCount " + portCount + ", portIndex " + portIndex);
//            }
        if (portCount == 1) {
            dp[i] = 2 + compute(boxes, dp, j, n, maxBoxes, maxWeight);
        } else {
            dp[i] = Math.min(portCount + 1 + compute(boxes, dp, j, n, maxBoxes, maxWeight), portCount + compute(boxes, dp, portIndex, n, maxBoxes, maxWeight));
        }

        return dp[i];
    }

    public static void main(String[] args) {
        System.out.println(new DeliverBox().boxDelivering(new int[][]{
                {2,4},{2,5},{3,1},{3,2},{3,7},{3,1},{4,4},{1,3},{5,2}
        }, 5, 5, 7));
    }
}