package com.ssynhtn.mock;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();

        char[] prefix = new char[n * 2];
        collect(res, prefix, 0, n, 0);
        return res;
    }

    private void collect(List<String> res, char[] prefix, int index, int remain, int surplus) {
        if (index == prefix.length) {
            res.add(new String(prefix));
            return;
        }

        if (surplus > 0) {
            prefix[index] = ')';
            collect(res, prefix, index + 1, remain, surplus - 1);
        }

        if (remain > 0) {
            prefix[index] = '(';
            collect(res, prefix, index + 1, remain - 1, surplus + 1);
        }

    }

    public static void main(String[] args) {
        System.out.println(new GenerateParenthesis().generateParenthesis(0));
    }
}
