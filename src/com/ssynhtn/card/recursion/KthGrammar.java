package com.ssynhtn.card.recursion;

public class KthGrammar {
    public int kthGrammar(int n, int k) {
        // 等价于计算k-1中1bit的奇偶性
        boolean debug = true;
        if (debug) {
            return bitParity(k - 1);
        }
        n--;
        k--;

        int s = 0;
        while (n > 0) {
//            System.out.println("n " + n + " k " + k);
            if (k >= (1 << (n-1))) {
                k = k - (1<<(n-1));
                s ^= 1;
            }
            n--;
        }
        return s;
    }

    private int bitParity(int k) {
        k = ((k >>> 16) ^ k) & 0xffff;
        k = ((k >>> 8) ^ k) & 0xff;
        k = ((k >>> 4) ^ k) & 0xf;
        k = ((k >>> 2) ^ k) & 3;
        k = ((k >>> 1) ^ k) & 1;
        return k;
    }


//    s = 0
//    s ^ 1
//
//
//    n > 0
//            if (k > 1<<(n-1))
//    k = 1<<n - k
//    s^^
//    n--;

    public static void main(String[] args) {
        System.out.println(new KthGrammar().kthGrammar(1, 1));
        System.out.println(new KthGrammar().kthGrammar(2, 1));
        System.out.println(new KthGrammar().kthGrammar(2, 2));
        System.out.println(new KthGrammar().kthGrammar(4, 5));
    }
}
