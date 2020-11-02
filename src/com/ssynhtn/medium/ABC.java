package com.ssynhtn.medium;


public class ABC {
    public boolean isValid(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        int i = 0;
        int j = 0;
        int diff = 0;
        while (j < n) {
            if (chs[j] == 'c') {
                if (i < 2) return false;
                if (chs[i-1] != 'b' || chs[i-2] != 'a') return false;
                i = i - 2;
                j++;
            } else {
                if (chs[j] == 'a') {
                    diff++;
                } else {
                    diff--;
                    if (diff < 0) return false;
                }
                chs[i++] = chs[j++];
            }
        }

        return i == 0;
    }

    public static void main(String[] args) {
        System.out.println(new ABC().isValid("aabcbc"));
    }
}
