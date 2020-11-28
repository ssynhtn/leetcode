package com.ssynhtn.challenge;

import com.ssynhtn.contest.LongestKeyStroke;

class LongestSubstringKFreq {
    public int longestSubstring(String s, int k) {
        if (k <= 1) return s.length();

        char[] chs = s.toCharArray();
        int n = chs.length;

        return rec(chs, 0, n, k);
    }

    // k >= 2
    private int rec(char[] chs, int i, int j, int k) {
        if (j - i < k) return 0;
        if (j - i == k) {
            for (int l = i; l < j; l++) {
                if (chs[l] != chs[i]) return 0;
            }

            return k;
        }

        int[] freqs = new int[26];
        for (int l = i; l < j; l++) {
            freqs[chs[l] - 'a']++;
        }

        boolean bad = false;
        for (int ch = 0; ch < 26; ch++) {
            if (freqs[ch] != 0 && freqs[ch] < k) {
                bad = true;
                break;
            }
        }
        if (!bad) return j - i;

        int l;
        int max = 0;
        for (l = i; l < j; l++) {
            if (freqs[chs[l] - 'a'] < k) {
                max = Math.max(max, rec(chs, i, l, k));
                i = l + 1;
            }
        }

        max = Math.max(max, rec(chs, i, j, k));
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringKFreq().longestSubstring("ababbc", 2));
    }
}