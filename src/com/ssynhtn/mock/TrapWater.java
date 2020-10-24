package com.ssynhtn.mock;

import oracle.jvm.hotspot.jfr.GlobalTraceBuffer;

import java.util.Arrays;

public class TrapWater {
    public int trap(int[] height) {
        int[] leftMax = new int[height.length];

        int sum = 0;

        int max = 0;
        for (int i = 0; i < height.length; i++) {
            max = leftMax[i] = Math.max(max, height[i]);
        }


        max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            sum += Math.min(leftMax[i], max = Math.max(max, height[i])) - height[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new TrapWater().trap(new int[] {
                0,1,0,2,1,0,1,3,2,1,2,1
        }));
    }
}
