package com.ssynhtn.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BitwiseOrSubArray {
    public int subarrayBitwiseORs(int[] A) {
        int[] buffer = new int[64];

        int i = 0;
        int size = 0;
        Set<Integer> res = new HashSet<>();

        for (int x : A) {
            int k = i;
            int k_1 = (i + 63) & 63;
            buffer[k_1] = x;
            res.add(x);

            int j;
            for (int c = 0; c < size; c++) {
                j = (i + c) & 63;
                int y = buffer[j] | x;
                if (y != buffer[k_1]) {
                    buffer[k] = y;
                    res.add(y);
                    k_1 = k;
                    k = (k + 1) & 63;
                }
            }

            i = (i + 63) & 63;
            size = (k + 64 - i) & 63;

//            System.out.println("i " + i + ", size " + size + " k " + k);
            printArray(buffer, i, size);
        }

        return res.size();
    }

    public int subarrayBitwiseORsInPlace(int[] A) {
        Set<Integer> res = new HashSet<>();

        int n = A.length;
        int size = 0;
        int j, k;
        for (int i = n-1; i>= 0; i--) {
            res.add(A[i]);
            k = i + 1;
            for (j = k; j <= i + size; j++) {
                int next = A[k-1] | A[j];
                if (next != A[k-1]) {
                    A[k++] = next;
                    res.add(next);
                }
            }

            size = k - i;
        }

        return res.size();
    }

    private void printArray(int[] buffer, int i, int size) {
        for (int j = 0; j < size; j++) {
            System.out.print(buffer[(i + j) % buffer.length]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(new BitwiseOrSubArray().subarrayBitwiseORsInPlace(new int[]{1, 2, 4}));
    }
}
