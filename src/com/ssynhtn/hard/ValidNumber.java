package com.ssynhtn.hard;

import javax.swing.*;

public class ValidNumber {
    // \s*(+|-)?(\d*)?(.\d*(e|E+|-\d*)?)?\s*
    public boolean isNumber(String s) {
        boolean hasLDigits = false;
        boolean hasRDigits = false;
        char[] chs = s.toCharArray();
        int index = 0;

        final int length = chs.length;

        char ch;
        // spaces
        while (index < length && chs[index] == ' ') {
            index++;
        }

        if (index == length) return false;
        if (chs[index] == '+' || chs[index] == '-') {
            index++;
        }

        if (index == length) return false;
        ch = chs[index++];
        if (ch >= '0' && ch <= '9') {
            hasLDigits = true;
            while (index < length && (ch = chs[index]) >= '0' && ch <= '9') {
                index++;
            }

            if (index == length) return true;
            ch = chs[index++];
        }

        if (ch == '.') {
            while (index < length && (ch = chs[index]) >= '0' && ch <= '9') {
                index++;
                hasRDigits = true;
            }

            if (index == length) {
                return hasLDigits || hasRDigits;
            }

            ch = chs[index++];
        }

        if (!hasLDigits && !hasRDigits) return false;

        if (ch == 'e' || ch == 'E') {
            if (index == length) return false;
            ch = chs[index++];

            if (ch == '+' || ch == '-') {
                if (index == length) return false;
                ch = chs[index++];
            }

            if (ch >= '0' && ch <= '9') {
                while (index < length && (ch = chs[index]) >= '0' && ch <= '9') {
                    index++;
                }
                if (index == length) return true;
                ch = chs[index++];
            } else {
                return false;
            }
        }

        if (ch != ' ') return false;
        while (index < length && chs[index] == ' ') {
            index++;
        }

        return index == length;

    }

    public static void main(String[] args) {
        String[] inputs = {
                "01",
                "0..",
                "2e0",
                ".1",
                "0",
                " 0.1 ",
                "abc",
                "1 a",
                "2e10",
                " -90e3   ",
                " 1e",
                "e3",
                " 6e-1",
                " 99e2.5 ",
                "53.5e93",
                " --6 ",
                "-+3",
                "95a54e53",
        };

        boolean[] expected = {
                true,
                false,
                true,
                true,
                true,
                true,
                false,
                false,
                true,
                true,
                false,
                false,
                true,
                false,
                true,
                false,
                false,
                false,
        };
        for (int i = 0; i < inputs.length; i++) {
            boolean isNum = new ValidNumber().isNumber(inputs[i]);
            System.out.println(inputs[i] + " is num? " + isNum + ", expected " + expected[i]);
            if (expected[i] != isNum) {
                System.out.println("!!!");
            }
        }
    }
}
