package com.ssynhtn.easy;

public class ReverseVowels {
    public String reverseVowels(String s) {
        char[] chs = s.toCharArray();
        int i = 0;
        int j = chs.length - 1;
        while (i < j) {
            while (i < j && !isVowel(chs[i])) {
                i++;
            }

            while (j > i && !isVowel(chs[j])) {
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

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }
}
