package com.ssynhtn.hard;

public class InterleavingStrings {
    public boolean isInterleave(String s1, String s2, String s3) {
//        System.out.println("a " + s1 + ", b " + s2 + ", c " + s3);
        int n = s1.length();
        int m = s2.length();
        int l = s3.length();
        if (n + m != l) return false;

        char[] as = s1.toCharArray();
        char[] bs = s2.toCharArray();
        char[] cs = s3.toCharArray();

        int[][] cache = new int[n+1][m+1];
        int res = isInterleave(as, 0, bs, 0, cs, 0, cache);
        return res == 1;
    }

    private int isInterleave(char[] as, int n, char[] bs, int m, char[] cs, int l, int[][] cache) {
        if (cache[n][m] != 0) return cache[n][m];
        if (n == as.length && m == bs.length) {
            return cache[n][m] = 1;
        }

//        n == len
//                [m, end) compare to [l,end)
//        m == len
//                [n, end) compare to [l, end)
//
//        if c[l] == a[n] && recurse(n+1, m, l+1) == 1
//        cache = 1, return 1
//        if c[l] == b[m] && recurse(n, m+1, l+1) == 1
//        cache = 1, return 1
//        cache = -1 return -1
        if (n == as.length) {
            return cache[n][m] = compare(bs, m, cs, l);
        }

        if (m == bs.length) {
            return cache[n][m] = compare(as, n, cs, l);
        }

        if (cs[l] == as[n] && isInterleave(as, n+1, bs, m, cs, l+1, cache) == 1) {
//            System.out.println("a " + as[n] + " at" + n +
//                    " == " + cs[l] + " at " + l + " and rest ok");
            return cache[n][m] = 1;
        }

        if (cs[l] == bs[m] && isInterleave(as, n, bs, m + 1, cs, l+1, cache) == 1) {
//            System.out.println("b " + bs[m] + " at" + m +
//                    " == " + cs[l] + " at " + l + " and rest ok");
            return cache[n][m] = 1;
        }

        return cache[n][m] = -1;
    }

    private int compare(char[] bs, int m, char[] cs, int l) {
        while (m < bs.length && bs[m] == cs[l]) {
            m++;
            l++;
        }

        return m == bs.length ? 1 : -1;
    }

    public static void main(String[] args) {
        System.out.println(new InterleavingStrings().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }
}
