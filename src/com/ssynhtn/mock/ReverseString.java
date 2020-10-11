package com.ssynhtn.mock;

import java.util.Arrays;

public class ReverseString {
    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) return;
        int i = 0;
        int j = s.length - 1;
        char ch;
        while (i < j) {
            ch = s[i];
            s[i] = s[j];
            s[j] = ch;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        char[] chs = "hello".toCharArray();
        new ReverseString().reverseString(chs);
        System.out.println(Arrays.toString(chs));
    }
}
