package com.ssynhtn.contest;

class ArrayStringEqual {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int i = 0;
        int j = 0;
        int i2 = 0;
        int j2 = 0;

        int n = word1.length;
        int m = word2.length;

        while (i < n && i2 < m) {
            String w1 = word1[i];
            String w2 = word2[i2];

            while (j < w1.length() && j2 < w2.length()) {
                if (w1.charAt(j) != w2.charAt(j2)) return false;
                j++;
                j2++;
            }

            if (j == w1.length()) {
                i++;
                j = 0;
            }
            if (j2 == w2.length()) {
                i2++;
                j2 = 0;
            }
        }

        return i == n && i2 == m;
    }

    public static void main(String[] args) {
        System.out.println(new ArrayStringEqual().arrayStringsAreEqual(new String[]{"abc", "d", "defg"}, new String[]{"abcddefg", "b"}));
    }
}