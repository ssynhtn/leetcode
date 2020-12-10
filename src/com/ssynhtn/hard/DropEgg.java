package com.ssynhtn.hard;

import sun.java2d.loops.DrawParallelogram;

import java.util.HashMap;
import java.util.Map;

public class DropEgg {
    // n >= 0, k >= 1
    // n <= 10000, k <= 100

    /**
     *
     f(1, n) -> n
     f(2, n)

     f(1,n) = min l (1+...1) >= n
     f(2, n) = min l (1+..l) >= n // 1,3,6,10,...
     f(3, n) =
     1,3,7,14,25,41
     1,2,4,7,11,16
     1,2,3,4,5


     k egg, l drop, 最多支持检测多大的n
     f(k, l)

     foo(1, l) = 1 + foo(1, l-1)
     foo(1, l) = l

     foo(2, l) = 1 + foo(1, l-1) + foo(2, l-1)
     = 1 + l-1 + foo(2, l-1)
     = l + foo(2, l-1)
     => foo(2, l) = 1+... + l = l(l+1)/2

     foo(3,l) = 1 + foo(2,l-1) + foo(3,l-1)
     = 1 + l(l-1)/2 + foo(3,l-1)

     foo(k, 1) = 1


     foo(3, 6) != foo(4, 6)
     ->l

     1 2 3 4 5 6
     1 3 6 10 14 21
     1 3 7 14 25 30 52
     1 3 7 15 30 56 87 140
     1 3 7 15 31 62 119 207


     最多计算 n*k
     */
    public int superEggDrop(int k, int n) {
        if (n == 0) return 0;
        if (k == 0) return Integer.MAX_VALUE;

        int bound = bits(n);
        if (k >= bound) {
            return bound;
        }

        int[] dp = new int[k];
        for (int i = 0; i < k; i++) {
            dp[i] = 1;
        }
        int count = 1;
        while (dp[k-1] < n) {
            for (int i = k-1; i > 0; i--) {
                dp[i] = dp[i] + dp[i-1] + 1;
            }
            dp[0]++;
            count++;
        }

        return count;

    }

    int bits(int x) {
        int c = 0;
        while (x > 0) {
            x = x >>> 1;
            c++;
        }
        return c;
    }

    public static void main(String[] args) {
//        System.out.println(new DropEgg().superEggDrop(4, 200));
//
//        System.out.println(new DropEgg().superEggDrop(3, 60));
//        System.out.println(new DropEgg().superEggDrop(4, 140));
//
//        System.out.println(new DropEgg().superEggDrop(3, 100));

//        System.out.println(new DropEgg().superEggDrop(2, 1250));
//        System.out.println(new DropEgg().superEggDrop(1, 3));

        for (int n = 0; n <= 100; n++) {
            System.out.print("n " + n + ", " + new DropEgg().superEggDrop(2, n) + "; ");
            System.out.print(new DropEgg().superEggDrop(3, n) + "; ");
            System.out.println(new DropEgg().superEggDrop(4, n));
        }
    }
}
