package com.ssynhtn.hard;

import java.util.Arrays;

public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        char[] ahs = s1.toCharArray();
        char[] bhs = s2.toCharArray();

        int n = ahs.length;
        // 0 <= i <= n-1, 0<=j<=n-1, 1<=len<=n
        // i, j, len-1
        int[][][] res = new int[n][n][n];

        return canScramble(ahs, 0, bhs, 0, n, res) > 0;
    }

    // len >= 1
    private int canScramble(char[] ahs, int i, char[] bhs, int j, int len, int[][][] res) {
//        System.out.println("checking " + new String(ahs, i, len) + " and " + new String(bhs, j, len));
        if (len == 1) return ahs[i] == bhs[j] ? 1 : -1;
        int lindex = len - 1;
        if (res[i][j][lindex] != 0) return res[i][j][lindex];

        if (!sameChars(ahs, i, bhs, j, len)) {
            res[i][j][lindex] = -1;
            return -1;
        }

        int[] diff = new int[26];
        int diffCount = 0;
        int k = i;
        int l = j;
        while (k < i + len - 1) {
            int a = ahs[k] - 'a';
            int b = bhs[l] - 'a';

            if (a != b) {
                int zeroCount = 0;
                if (diff[a] == 0) {
                    zeroCount++;
                }
                if (diff[b] == 0) {
                    zeroCount++;
                }
                diff[a]++;
                diff[b]--;
                diffCount -= zeroCount;
                if (diff[a] == 0) {
                    diffCount++;
                }
                if (diff[b] == 0) {
                    diffCount++;
                }

            }

            if (diffCount == 0 && canScramble(ahs, i, bhs, j, k-i+1, res) > 0 && canScramble(ahs, k+1, bhs, j+k-i+1, len-k+i-1, res) > 0) {

                res[i][j][lindex] = 1;
                return 1;
            }
            k++;
            l++;
        }

        Arrays.fill(diff, 0);
        diffCount = 0;
        k = i;
        l = j + len - 1;
        while (k < i + len - 1) {
            int a = ahs[k] - 'a';
            int b = bhs[l] - 'a';

            if (a != b) {
                int zeroCount = 0;
                if (diff[a] == 0) {
                    zeroCount++;
                }
                if (diff[b] == 0) {
                    zeroCount++;
                }
                diff[a]++;
                diff[b]--;
                diffCount -= zeroCount;
                if (diff[a] == 0) {
                    diffCount++;
                }
                if (diff[b] == 0) {
                    diffCount++;
                }

            }

            if (diffCount == 0 && canScramble(ahs, i, bhs, l, k-i+1, res) > 0 &&
            canScramble(ahs, k+1, bhs, j, len -k+i-1, res) > 0) {
                res[i][j][lindex] = 1;
                return 1;
            }
            k++;
            l--;
        }

        res[i][j][lindex] = -1;
        return res[i][j][lindex];
    }

    private boolean sameChars(char[] ahs, int i, char[] bhs, int j, int len) {
        int[] buffer1 = new int[26];
        int[] buffer2 = new int[26];
        int i2 = i + len;
        while (i < i2) {
            buffer1[ahs[i] - 'a']++;
            buffer2[bhs[j] - 'a']++;
            i++;
            j++;
        }
        for (int k = 0; k < 26; k++) {
            if (buffer1[k] != buffer2[k]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ScrambleString().isScramble("abcde", "caebd"));
    }
}
