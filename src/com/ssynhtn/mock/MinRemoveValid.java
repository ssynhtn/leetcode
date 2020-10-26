package com.ssynhtn.mock;

public class MinRemoveValid {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int sup = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                sup++;
                sb.append(ch);
            } else if (ch == ')') {
                if (sup > 0) {
                    sup--;
                    sb.append(ch);
                }
            } else {
                sb.append(ch);
            }
        }

        if (sup > 0) {
            int i = sb.length() - 1;
            while (sup > 0) {
                while (i >= 0 && sb.charAt(i) != '(') {
                    i--;
                }
                // i >= 0
                sb.delete(i, i + 1);
                sup--;
                i--;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new MinRemoveValid().minRemoveToMakeValid("(a(b(c)d)"));
    }
}
