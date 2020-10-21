package com.ssynhtn.medium;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SumofMutatedArrayClosesttoTarget {
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;

        int sum = 0;
        int i = 0;

        while (i < n && sum < target) {
            sum += arr[i];
            i++;
        }

        if (sum <= target) return arr[n-1];

        int d = arr[i-1];
        sum += d * (n - i);
        i--;

        System.out.println("if from " + i + " are " + arr[i] + "s, sum would be " + sum);
        int diff = sum - target;
        while (i > 0) {
            int c = arr[i - 1];
            int reduce = (d - c) * (n - i);
            System.out.println("d " + d + " c " + c + ", potential reduce " + reduce + ", diff " + diff);


            if (reduce == diff) return c;
            if (reduce > diff) {
                int u = diff / (n - i);
                System.out.println("" + (d-u) + " would lead to " + Math.abs(diff - u * (n - i)));
                System.out.println("" + (d-u-1) + " would lead to " + Math.abs(diff - (u + 1) * (n - i)));
                if (Math.abs(diff - u * (n - i)) < Math.abs(diff - (u + 1) * (n - i))) {
                    return d - u;
                } else {
                    return d - u - 1;
                }
            }

            diff = diff - reduce;
            i--;
            d = c;
        }

        int u = diff / n;
        if (Math.abs(diff - u * n) < Math.abs(diff - (u + 1) * n)) {
            return d - u;
        }

        return d - u - 1;

//        abcddddd
//
//        i开始有n-i个d
//
//        diff = sum - target
//
//        i > 0: c = nums[i-1]
//        1, (d-c)*(n-i) == diff
//        return c
//        2, (d-c)*(n-i) > diff
//
//                (d-u)*(n-i) ~ diff
//
//        diff / (n-i)整数
//                u = d- diff/n-i
//
//        u = [that]
//        abs(diff-(d-u)*(n-i)) < abs(// u+1) : d-u, else d-(u+1)
//                3, (d-c)*(n-i) < diff
//        diff = diff - (d-c)(n-i);
//        i--
//        d = c
//
//        i == 0
//
//        case 2


    }

    public static void main(String[] args) {
        int me = 1547 + 17423 * 4;
        int t = 1547 + 17422 * 4;
        System.out.println("me " + me + " t " + t + ", target " + 71237);
        System.out.println(new SumofMutatedArrayClosesttoTarget().findBestValue(new int[]{1547,83230,57084,93444,70879}, 71237));
    }
}
