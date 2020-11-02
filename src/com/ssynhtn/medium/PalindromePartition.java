package com.ssynhtn.medium;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartition {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> prefix = new ArrayList<>();
        collect(s, 0, prefix, res, s.length());
        return res;
    }

    private void collect(String s, int i, List<String> prefix, List<List<String>> res, int n) {
        if (i == n) {
            res.add(new ArrayList<>(prefix));
            return;
        }

        for (int j = i; j < n; j++) {
            if (isPal(s, i, j)) {
                prefix.add(s.substring(i, j + 1));
                collect(s, j + 1, prefix, res, n);
                prefix.remove(prefix.size() - 1);
            }
        }



    }

    private boolean isPal(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartition().partition("aab"));
    }
}
