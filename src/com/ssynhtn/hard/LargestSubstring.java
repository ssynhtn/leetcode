package com.ssynhtn.hard;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LargestSubstring {

    public String lastSubstring(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;

        int i = n-1;

        // [i, ~) current largest
        for (int j = n - 2; j >= 0; j--) {
            if (chs[j] < chs[i]) continue;

            // jth >= ith
            int l = j;
            int r = i;
            while (l < i && r < n && chs[l] == chs[r]) {
                l++;
                r++;
            }

            if (r == n || l == i || chs[l] > chs[r]) {
                i = j;
            }
        }

        return new String(chs, i, n - i);

    }
    public String lastSubstringZ(String s) {
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
        int j0 = j;
        if (j == i + 1) return true;
        int n = chs.length;
        while (i < j0 && j < n && chs[i] == chs[j]) {
            i++;
            j++;
        }

        if (j == n) return true;
        if (i == j0) return true;
        return chs[i] > chs[j];
    }

    public static void main(String[] args) {
        char[] chs = new char[10];
        Arrays.fill(chs, 'a');
        System.out.println(new LargestSubstring().lastSubstring(new String(chs)));
    }
}