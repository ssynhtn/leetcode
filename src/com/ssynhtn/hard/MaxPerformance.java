package com.ssynhtn.hard;

import java.awt.image.ImageProducer;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MaxPerformance {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[] buffer = new int[n];
        sort(speed, efficiency, buffer, 0, n);
//        System.out.println(Arrays.toString(speed));
//        System.out.println(Arrays.toString(efficiency));

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        long sum = 0;
        int minEffi = Integer.MAX_VALUE;
        long maxPerf = 0;
        for (int i = 0; i < n; i++) {
            heap.add(speed[i]);
            sum += speed[i];
            minEffi = Math.min(minEffi, efficiency[i]);
            if (i >= k) {
                sum -= heap.remove();
            }
            maxPerf = Math.max(maxPerf, sum * minEffi);
        }


        return (int) (maxPerf % (1000000007));
    }

    private void sort(int[] speed, int[] efficiency, int[] buffer, int left, int right) {
        if (right - left <= 1) return;

        int mid = left + (right - left) / 2;
        sort(speed, efficiency, buffer, left, mid);
        sort(speed, efficiency, buffer, mid, right);

        merge(speed, efficiency, buffer, left, mid, right);
    }

    private void merge(int[] speed, int[] efficiency, int[] buffer, int left, int mid, int right) {
        System.arraycopy(efficiency, left, buffer, left, mid - left);
        System.arraycopy(speed, left, buffer, mid, mid - left);

        int i = left;
        int j = mid;
        int k = left;
        int i2 = mid;
        int j2 = mid;
        int k2 = left;

        while (i < mid && j < right) {
            if (buffer[i] > efficiency[j]) {
                efficiency[k++] = buffer[i++];
                speed[k2++] = buffer[i2++];
            } else {
                efficiency[k++] = efficiency[j++];
                speed[k2++] = speed[j2++];
            }
        }

        while (i < mid) {
            efficiency[k++] = buffer[i++];
            speed[k2++] = buffer[i2++];
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaxPerformance().maxPerformance(3, new int[]{2,8,2}, new int[]{2,7,1}, 2));
    }
}
