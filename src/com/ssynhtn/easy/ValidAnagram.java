package com.ssynhtn.easy;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        int n = s.length();
        if (t.length() != n) return false;
        int[] a = freq(s);
        int[] b = freq(t);
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    private int[] freq(String s) {
        int[] f = new int[26];
        for (char ch : s.toCharArray()) {
            f[ch - 'a']++;
        }
        return f;
    }
}
