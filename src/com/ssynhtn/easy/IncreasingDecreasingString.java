package com.ssynhtn.easy;

public class IncreasingDecreasingString {
    public String sortString(String s) {
        int n = s.length();
        char[] dict = new char[26];
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            dict[ch - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            for (int i = 0; i < 26; i++) {
                if (dict[i] > 0) {
                    sb.append((char)('a' + i));
                    dict[i]--;
                    n--;
                }
            }

            if (n > 0) {
                for (int i = 25; i >= 0; i--) {
                    if (dict[i] > 0) {
                        sb.append((char)('a' + i));
                        dict[i]--;
                        n--;
                    }
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new IncreasingDecreasingString().sortString("aaaabbbbcccc"));
    }
}
