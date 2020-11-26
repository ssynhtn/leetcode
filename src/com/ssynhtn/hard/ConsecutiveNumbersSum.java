package com.ssynhtn.hard;

import com.ssynhtn.challenge.ConsecutiveChars;

class ConsecutiveNumbersSum {
    public int consecutiveNumbersSum(int n) {
       while (n % 2 == 0) {
           n = n / 2;
       }

       int lower = 1;
       int res = 1;
       while (n > 1) {
           int start = lower + 1;
           if (n < start * start) {
                // n is prime
               res = res * 2;
               break;
           }

           int limit = (int) Math.floor(Math.sqrt(n));
           int p = -1;
           for (int i = start; i <= limit; i++) {
               if (n % i == 0) {
                   p = i;
                   break;
               }
           }

           if (p == -1) {
               // n prime
               res = res * 2;
               break;
           }

           int e = 1;
           n = n / p;
           while (n % p == 0) {
               e++;
               n = n / p;
           }

           res = res * (e + 1);
           lower = p;
       }

       return res;
    }

    public static void main(String[] args) {
        System.out.println(new ConsecutiveNumbersSum().consecutiveNumbersSum(1000000006));
    }
}