package com.ssynhtn.medium;

import com.ssynhtn.mock.ReorderLogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ReOrgnizeString {
    static class CharCount {
        char ch;
        int count;

        public CharCount(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }
    public String reorganizeString(String S) {
        int[] counts = new int[26];
        int n = S.length();
        for (char ch : S.toCharArray()) {
            counts[ch - 'a']++;
        }
        int max = 0;
        for (int i = 0; i < 26; i++) {
            max = Math.max(max, counts[i]);
        }
        if (max > n - max + 1) {
            return "";
        }

        CharCount[] charCounts = new CharCount[26];

        for (int i = 0; i < 26; i++) {
            char ch = (char) (i + 'a');
            int count = counts[i];
            charCounts[i] = new CharCount(ch, count);
        }
        Arrays.sort(charCounts, new Comparator<CharCount>() {
            @Override
            public int compare(CharCount o1, CharCount o2) {
                return o2.count - o1.count;
            }
        });
        int j = 0;
        while (j < 26 && charCounts[j].count > 0) {
            j++;
        }
        j--;

        StringBuilder sb = new StringBuilder();
        while (charCounts[0].count > 0) {
            if (charCounts[0].count == charCounts[1].count) {
                int c = charCounts[0].count;
                for (int i = 0; i < 26 && charCounts[i].count == c; i++) {
                    sb.append(charCounts[i].ch);
                    charCounts[i].count--;
                }
            } else {
                sb.append(charCounts[0].ch);
                charCounts[0].count--;
                if (j > 0) {
                    sb.append(charCounts[j].ch);
                    charCounts[j].count--;
                    if (charCounts[j].count == 0) {
                        j--;
                    }
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReOrgnizeString().reorganizeString("aab"));
    }

}
