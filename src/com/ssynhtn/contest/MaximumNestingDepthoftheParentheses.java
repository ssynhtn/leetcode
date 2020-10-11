package com.ssynhtn.contest;

public class MaximumNestingDepthoftheParentheses {
    public int maxDepth(String s) {
        int depth = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                depth++;
                max = Math.max(max, depth);
            } else if (ch == ')') {
                depth--;
            }
        }

        return max;

    }

    public static void main(String[] args) {
        System.out.println(new MaximumNestingDepthoftheParentheses().maxDepth("(1)+((2))+(((3)))"));
    }
}
