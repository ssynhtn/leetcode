package com.ssynhtn.medium;

import java.util.*;

public class LenLongestFibSubseq {
    static int[] FIB = {1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,10946,17711,28657,46368,75025,121393,196418,317811,514229,832040,1346269,2178309,3524578,5702887,9227465,14930352,24157817,39088169,63245986,102334155,165580141,267914296,433494437,701408733};

    public int lenLongestFibSubseqDp2(int[] A) {
        int n = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(A[i], i);
        }
        int[][] dp = new int[n][n];
        /*
        i < j
        dp[i, j], ends with i, j的最大length
        c = A[j]-A[i]
        c -> k
        A[i,k] + 1
         */
        int max = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int c = A[j] - A[i];
                if (c >= A[i]) continue;
                Integer k = map.get(c);
                if (k != null) {
                    dp[i][j] = dp[k][i] == 0 ? 3 : dp[k][i] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        return max;
    }

    /**
     * 当前最长 max
     *
     * 如果len >= max + 1
     *
     * a, b, a + b a + 2b
     *
     * g[2] -> f[0]a + f[1]b
     * g[3] -> f[1]a + f[2]b
     *
     * g[max] = f[max-2]a + f[max-1]b
     *
     * g[max] > last => skip
     */
    public int lenLongestFibSubseq(int[] A) {
        int n = A.length;   // n >= 3
        int max = 2;
        int largest = A[n-1];

        Set<Integer> nums = new HashSet<>();
        for (int x : A) {
            nums.add(x);
        }

        for (int i = 0; i < n-1; i++) {
            long last = FIB[max-1] * A[i] + FIB[max-2] * A[i+1];
            if (last > largest) continue;

            int count = 2;
            int a = A[i];
            int b = A[i+1];
            while (true) {
                int c = a + b;
                if (nums.contains(c)) {
                    count++;
                    a = b;
                    b = c;
                } else {
                    break;
                }
            }

            max = Math.max(max, count);

            for (int j = i + 2; j < n; j++) {
                last = FIB[max-1] * A[i] + FIB[max-2] * A[j];
                if (last > largest) continue;
                count = 2;
                a = A[i];
                b = A[j];
                while (true) {
                    int c = a + b;
                    if (nums.contains(c)) {
                        count++;
                        a = b;
                        b = c;
                    } else {
                        break;
                    }
                }

                max = Math.max(max, count);

            }
        }

        return max == 2 ? 0 : max;
    }

    public int lenLongestFibSubseqDp(int[] A) {
        int n = A.length;
        int[] max = new int[n];
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (max[j] == 0 && max[i] != 0) continue;
                if (max[j] != 0 && prev[j] + A[j] == A[i]) {
                    if (max[j] >= max[i]) {
                        max[i] = max[j] + 1;
                        prev[i] = A[j];
                    }
                } else {
                    int b = A[i];
                    int a = A[j];
                    int k = j;
                    int count = 2;
                    while (k > 0) {
                        int index = Arrays.binarySearch(A, 0, k, b-a);
                        if (index < 0) break;
                        count++;
                        b = a;
                        a = A[index];
                        k = index;
                    }

                    if (count > 2 && count > max[i]) {
                        max[i] = count;
                        prev[i] = A[j];
                    }
                }
            }
        }

        int res = 0;
        for (int m : max) {
            res = Math.max(res, m);
        }

        return res;
    }


    public int lenLongestFibSubseqBrute(int[] A) {
        Set<Integer> nums = new HashSet<>();
        for (int x : A) {
            nums.add(x);
        }

        int n = A.length;
        int max = 2;
        for (int i = 0; i < n-1; i++) {
            for (int j = i + 1; j < n; j++) {
                int count = 2;
                int a = A[i];
                int b = A[j];
                while (true) {
                    int c = a + b;
                    if (nums.contains(c)) {
                        count++;
                        a = b;
                        b = c;
                    } else {
                        break;
                    }
                }

//                int k = j + 1;
//                while (k < n) {
//                    int c = a + b;
//                    int index = Arrays.binarySearch(A, k, n, c);
//                    if (index < 0) break;
//                    count++;
//                    a = b;
//                    b = c;
//                    k = index + 1;
//                }

                max = Math.max(max, count);
            }
        }
//        int[] lens = new int[n];
//        int[] nexts = new int[n];
//
//        for (int i = n-3; i >= 0; i--) {
//            for (int j = i+1; j < n; j++) {
//                if (lens[j] == 0 && lens[i] != 0) continue;
//                if (nexts[j] == A[j] + A[i]) {
//                    //
//                }
//            }
//        }

        return max > 2 ? max : 0;
    }

    static void fib() {
        int a = 1;
        int b = 1;
        System.out.print(a + ",");
        while (b <= 1000000000) {
            System.out.print(b + ",");
            int c = a + b;
            a = b;
            b = c;
        }
    }

    public static void main(String[] args) {
//        fib();
        System.out.println(new LenLongestFibSubseq().lenLongestFibSubseqDp2(new int[]{1,2,3,4,5,6,7,8}));
    }
}