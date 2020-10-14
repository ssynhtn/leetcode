package com.ssynhtn.mock;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> collect = new ArrayList<>();

        if (digits == null || digits.length() == 0) return collect;

        char[][] dict = new char[10][];
        dict[0] = new char[]{' '};
        dict[2] = new char[]{'a', 'b', 'c'};
        dict[3] = new char[]{'d', 'e', 'f'};
        dict[4] = new char[]{'g', 'h', 'i'};
        dict[5] = new char[]{'j', 'k', 'l'};
        dict[6] = new char[]{'m', 'n', 'o'};
        dict[7] = new char[]{'p', 'q', 'r', 's'};
        dict[8] = new char[]{'t', 'u', 'v'};
        dict[9] = new char[]{'w', 'x', 'y', 'z'};

        char[] prefix = new char[digits.length()];

        collect(collect, prefix, 0, digits, dict);

        return collect;
    }

    private void collect(List<String> collect, char[] prefix, int k, String digits, char[][] dict) {
        if (k == prefix.length) {
            collect.add(new String(prefix));
            return;
        }

        int d = digits.charAt(k) - '0';
        for (char ch : dict[d]) {
            prefix[k] = ch;
            collect(collect, prefix, k + 1, digits, dict);
        }

    }

    public static void main(String[] args) {
        System.out.println(new LetterCombinations().letterCombinations("").size());
    }
}
