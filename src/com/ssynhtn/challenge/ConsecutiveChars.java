package com.ssynhtn.challenge;

public class ConsecutiveChars {
    public int maxPower(String s) {
        int current = 0;
        int max = 0;
        char prev = ' ';
        for (char ch : s.toCharArray()) {
            if (ch == prev) {
                current++;
            } else {
                max = Math.max(max, current);
                current = 1;
                prev = ch;
            }
        }

        max = Math.max(max, current);
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new ConsecutiveChars().maxPower("leetcode"));
    }
}
