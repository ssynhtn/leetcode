package com.ssynhtn.hard;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LargestSubstring {

    public String lastSubstring(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        List<Integer>[] indexes = new List[26];

        for (int i = 0; i < 26; i++) {
            indexes[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            indexes[chs[i] - 'a'].add(i);
        }

        List<Integer> indice = null;
        for (char ch = 'z'; ch >= 'a'; ch--) {
            if (!indexes[ch - 'a'].isEmpty()) {
                indice = indexes[ch - 'a'];
                break;
            }
        }

        if (indice == null) return "";

        int maxIndex = indice.get(indice.size() - 1);
        for (int i = indice.size() - 2; i >= 0; i--) {
            if (larger(chs, indice.get(i), maxIndex)) {
                maxIndex = indice.get(i);
            }
        }

        return new String(chs, maxIndex, n - maxIndex);
    }

    // i < j
    private boolean larger(char[] chs, int i, int j) {
        if (j == i + 1) return true;
        int n = chs.length;
        while (i < n && j < n && chs[i] == chs[j]) {
            i++;
            j++;
        }

        if (j == n) return true;
        return chs[i] > chs[j];
    }

    public static void main(String[] args) {
        char[] chs = new char[100000];
        Arrays.fill(chs, 'a');
        System.out.println(new LargestSubstring().lastSubstring(new String(chs)));
    }
}