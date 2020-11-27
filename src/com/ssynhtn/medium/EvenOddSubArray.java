package com.ssynhtn.medium;

import javax.crypto.spec.PSource;

class EvenOddSubArray {
    static final int N = 1000000007;
    public int numOfSubarrays(int[] arr) {
        int even = 1;
        int odd = 0;
        int sum = 0;
        for (int x : arr) {
            if (x % 2 != 0) {
                int temp = even;
                even = odd + 1;
                odd = temp;
            } else {
                even++;
            }
            
            
            sum += odd;
            sum = sum % N;
        }
        
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new EvenOddSubArray().numOfSubarrays(new int[]{2,4,6}));
    }
    
}