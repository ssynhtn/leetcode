package com.ssynhtn.easy;


import com.oracle.javafx.jmx.SGMXBean;

import java.util.Arrays;

public class ReverseWords {
    public String reverseWords(String s) {
        if (s == null) return null;

        int n = s.length();
        if (n == 0) return "";
        char[] ss = s.toCharArray();

        int i = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && ss[j] != ' ') {
                j++;
            }

            int k = j - 1;
            while (i < k) {
                char ch = ss[i];
                ss[i] = ss[k];
                ss[k] = ch;
                i++;
                k--;
            }

            i = j + 1;
        }

        return new String(ss);
    }

    public String reverseWordsSB(String s) {
        if (s == null) return null;
        if (s.length() == 0) return "";

        String[] tokens = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String t : tokens) {
            for (int i = t.length() - 1; i >= 0; i--) {
                sb.append(t.charAt(i));
            }
            sb.append(' ');
        }

        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();

    }

    public String reverseOnlyLetters(String S) {
        if (S == null) return null;
        char[] chs = S.toCharArray();
        int i = 0, j = chs.length - 1;
        while (i < j) {
            while (i < j && !Character.isAlphabetic(chs[i])) {
                i++;
            }
            while (j > i && !Character.isAlphabetic(chs[j])) {
                j--;
            }

            if (i < j) {
                char temp = chs[i];
                chs[i] = chs[j];
                chs[j] = temp;
                i++;
                j--;
            }
        }

        return new String(chs);
    }


    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            if (target[i] != arr[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseWords().reverseOnlyLetters("a-bC-dEf-ghIj"));
    }
}
