package com.ssynhtn.hard;


import java.util.Arrays;

public class MinWindowSubstring {
    public String minWindow(String s, String t) {
        System.out.println(s);
        System.out.println(t);
        int n = s.length();
        int m = t.length();
        if (n < m) return "";

        int[] tCounts = new int[52];
        int[] sCounts = new int[52];

        for (char ch : t.toCharArray()) {
            tCounts[charIndex(ch)]++;
            System.out.println("index of " + ch + " is " + charIndex(ch));
        }

        System.out.println(Arrays.toString(tCounts));

        char[] chs = s.toCharArray();
        int i = 0;
        for (int j = 0; j < 52; j++) {
            while (sCounts[j] < tCounts[j] && i < n) {
                char ch = chs[i++];
                int index = charIndex(ch);
                System.out.println("index of " + ch + " is " + index);
                sCounts[index]++;
            }

            if (sCounts[j] >= tCounts[j]) {
                System.out.println("s has more " + j);
            } else {
                System.out.println("s has less " + j);
                // i == n
                return "";
            }
        }

        System.out.println(Arrays.toString(sCounts));

        int start = 0;
        int minStart = 0;
        int minLen = i;

        while (true) {
            char ch = chs[start++];
            int index = charIndex(ch);
            sCounts[index]--;

            if (sCounts[index] >= tCounts[index]) {
//                System.out.println("removing " + ch + " still ok");
                if (i - start < minLen) {

                    minStart = start;
                    minLen = i - start;
                }
            } else {
//                System.out.println("removing " + ch + " not ok, counts in window now " + sCounts[index] + ", required " + tCounts[index]);
                boolean found = false;
                while (i < n) {
                    char temp = chs[i++];
                    sCounts[charIndex(temp)]++;
                    if (temp == ch) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
//                    System.out.println("not found~ " + minStart + ", " + minEnd);
                    break;
                }
//                System.out.println("removing " + ch + " requires us to extend to " + (i-1) + " " + chs[i-1]);

            }

        }



        return new String(chs, minStart, minLen);
    }

    int charIndex(char ch) {
        if (ch < 'a') {
            return ch - 'A';
        }

        return ch - 'a' + 26;
    }

    public static void main(String[] args) {
        System.out.println(new MinWindowSubstring().minWindow("cabefgecdaecf", "cae"));
    }

}
