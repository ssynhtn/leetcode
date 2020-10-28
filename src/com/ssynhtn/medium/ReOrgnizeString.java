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
                if (charCounts[1].count > 0) {
                    sb.append(charCounts[1].ch);
                    charCounts[1].count--;

                    for (int i = 1; i + 1 < 26; i++) {
                        if (charCounts[i].count < charCounts[i+1].count) {
                            CharCount temp = charCounts[i];
                            charCounts[i] = charCounts[i+1];
                            charCounts[i+1] = temp;
                        }
                    }
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReOrgnizeString().reorganizeString("aaab"));
    }

}
