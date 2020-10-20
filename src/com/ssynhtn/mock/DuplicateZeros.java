package com.ssynhtn.mock;

import java.util.Arrays;

public class DuplicateZeros {
    public void duplicateZeros(int[] arr) {
        int i = 0;
        int count = 0;

        while (count < arr.length) {
            if (arr[i] != 0) {
                count++;
            } else {
                count += 2;
            }
            i++;
        }

        i--;
        count--;

        if (count == arr.length) {
            arr[arr.length - 1] = 0;
            count -= 2;
            i--;
        }

        while (i >= 0) {
            if (arr[i] == 0) {
                arr[count--] = 0;
                arr[count--] = 0;
            } else {
                arr[count--] = arr[i];
            }
            i--;
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[]{8,4,5,0,0,0,0,7};
        new DuplicateZeros().duplicateZeros(arr);
        System.out.println(Arrays.toString(arr));
    }
}
