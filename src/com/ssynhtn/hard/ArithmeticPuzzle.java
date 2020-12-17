package com.ssynhtn.hard;

import java.awt.color.CMMException;
import java.util.Arrays;

class ArithmeticPuzzle {
    /**
     * 2 <= words.length <= 5
     * 1 <= words[i].length, result.length <= 7
     * words[i], result contains only upper case English letters.
     * Number of different characters used on the expression is at most 10.
     *
     */
    public boolean isSolvable(String[] words, String result) {
//        long start = System.currentTimeMillis();
        int maxLen = 0;
        for (String w : words) {
            maxLen = Math.max(maxLen, w.length());
        }

        int sumLen = result.length();

        if (sumLen > maxLen + 1 || sumLen < maxLen) {
            return false;
        }

        int[] dMap = new int[10];
        int[] cMap = new int[26];
        Arrays.fill(cMap, -1);

        char[][] ws = new char[words.length][sumLen];
        for (int i = 0; i < words.length; i++) {
            System.arraycopy(words[i].toCharArray(), 0, ws[i], sumLen - words[i].length(), words[i].length());
        }

        char[] sumChs = result.toCharArray();
        if (sumLen > maxLen) {
            char ch = result.charAt(0);
            for (int d = 1; d < maxLen; d++) {
                cMap[ch - 'A'] = d;
                dMap[d] = ch;

                if (isSolvable(ws, sumChs, dMap, cMap, result.length(), words.length, sumLen - 1, 0, 0)) {
//                    System.out.println("took " + (System.currentTimeMillis() - start));
                    return true;
                }
                cMap[ch - 'A'] = -1;
                dMap[d] = 0;
            }

//            System.out.println("took " + (System.currentTimeMillis() - start));
            return false;
        }

        if (isSolvable(ws, sumChs, dMap, cMap, result.length(), words.length, sumLen - 1, 0, 0)) {
//            for (int i = 0; i < 26; i++) {
//                if (cMap[i] >= 0) {
//                    System.out.println((char)(i + 'A') + " -> " + cMap[i]);
//                }
//            }
//            for (String w : words) {
//                for (char ch : w.toCharArray()) {
//                    System.out.print(cMap[ch - 'A']);
//                }
//                System.out.println();
//            }
//            for (char ch : result.toCharArray()) {
//                System.out.print(cMap[ch - 'A']);
//            }
//            System.out.println();
//            System.out.println("took " + (System.currentTimeMillis() - start));
            return true;
        }

//        System.out.println("took " + (System.currentTimeMillis() - start));
        return false;

    }

    /**
     *
     ch = w[j][i]
     j < words.len:
     if ch is 0 or defined:
     rec j + 1
     else:
     for each possible d for ch
     take d, if rec, then true
     backtrack d
     return false
     j == words.len
     sum ith row, add carry, result to new sum and carry
     if ch defined
     if match, rec to i + 1
     else false
     if ch not defined:
     take sum, if rec to i + 1 then true
     else backtrack and return false
     */
    private boolean isSolvable(char[][] ws, char[] sums, int[] dMap, int[] cMap, int n, int m, int i, int j, int carry) {
        if (i < 0) {
            return carry == 0;
        }

        if (j < m) {
            char ch = ws[j][i];
            // 此处有字母， 字母的值定义为0， 此处为首字母
            if (ch != 0 && cMap[ch - 'A'] == 0 && (i == 0 || ws[j][i-1] == 0)) {
                if (i != n-1) {
                    return false;
                }
            }

            if (ch == 0 || cMap[ch - 'A'] >= 0) {
                return isSolvable(ws, sums, dMap, cMap, n, m, i, j + 1, carry);
            }

            for (int d = 0; d <= 9; d++) {
                if (dMap[d] != 0) continue;
                // 首字母不能为0
                if (d == 0 && (i == 0 || ws[j][i-1] == 0) && i != n-1) continue;
                dMap[d] = ch;
                cMap[ch - 'A'] = d;
//                System.out.println("putting " + ch + " as " + d);
                if (isSolvable(ws, sums, dMap, cMap, n, m, i, j + 1, carry)) return true;
                dMap[d] = 0;
                cMap[ch - 'A'] = -1;
//                System.out.println("unputting " + ch + " as " + d);
            }

            return false;
        } else {
            int s = 0;
            for (int k = 0; k < m; k++) {
                char ch = ws[k][i];
                if (ch == 0) continue;
                s += cMap[ch - 'A'];
            }

            s += carry;
            carry = s / 10;
            s = s % 10;

            char ch = sums[i];
            int d = cMap[ch - 'A'];
            // 首字母为0
            if (d == 0 && i == 0 && i != n-1) return false;
            if (d >= 0) {
                 if (d != s) return false;
                 return isSolvable(ws, sums, dMap, cMap, n, m, i-1, 0, carry);
            } else {
                if (dMap[s] != 0) return false;
                if (s == 0 && i == 0) return false;
                dMap[s] = ch;
                cMap[ch - 'A'] = s;
//                System.out.println("putting " + ch + " as " + s);
                if (isSolvable(ws, sums, dMap, cMap, n, m, i-1, 0, carry)) {
                    return true;
                }
                dMap[s] = 0;
                cMap[ch - 'A'] = -1;
//                System.out.println("unputting " + ch + " as " + s);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new ArithmeticPuzzle().isSolvable(new String[]{"SEND", "MORE"}, "MONEY"));
    }

}