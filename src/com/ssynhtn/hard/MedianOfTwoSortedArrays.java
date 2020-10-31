package com.ssynhtn.hard;


import java.util.Arrays;

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = 0;
        int r1 = nums1.length;
        int l2 = 0;
        int r2 = nums2.length;

        int n1 = r1;
        int n2 = r2;
        while (n1 >= 4 && n2 >= 4) {
            int m1 = l1 + n1 / 2;
            // m2 - l2 + 1 + m1 - l1 + 1 = N/2 + 1
            // m2 = l2 + N/2 - 1 - n1/2
            int m2 = (n1 + n2)/2 + l1 + l2 - m1 - 1;

            if (nums1[m1] <= nums2[m2]) {
                int remove = Math.min(m1 - l1, r2 - m2 - 1);
                l1 += remove;
                r2 -= remove;
                n1 -= remove;
                n2 -= remove;
            } else {
                int remove = Math.min(m2 - l2, r1 - m1 - 1);
                l2 += remove;
                r1 -= remove;
                n1 -= remove;
                n2 -= remove;
            }
        }

        int extra;
        if (n2 > n1 && (extra = n2 - (n1+n2)/2 - 1) > 0) {
            l2 += extra;
            r2 -= extra;
            n2 -= 2*extra;
        } else if (n1 > n2 && (extra = n1 - (n1+n2)/2 - 1) > 0) {
            l1 += extra;
            r1 -= extra;
            n1 -= 2*extra;
        }


        int[] buffer = new int[n1 + n2];
        int k = 0;
        for (int i = l1; i < r1; i++) {
            buffer[k++] = nums1[i];
        }
        for (int i = l2; i < r2; i++) {
            buffer[k++] = nums2[i];
        }
        Arrays.sort(buffer);
        int n = n1 + n2;
        if (n % 2 == 0) {
            return (buffer[n/2-1] + buffer[n/2]) / 2.0;
        } else {
            return buffer[n / 2];
        }
    }

    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[]{

        }, new int[]{
1,2,3,4,5,6,
        }));
    }
}
