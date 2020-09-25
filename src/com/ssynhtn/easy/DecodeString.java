package com.ssynhtn.easy;

import java.util.ArrayDeque;

public class DecodeString {
    // 3[a2[c]] => accaccacc
    public String decodeString(String s) {
        if (s == null) return null;
        ArrayDeque<String> stack = new ArrayDeque<>();
        int i = 0;
        char ch;
        while (i < s.length()) {
            ch = s.charAt(i);
            if (ch == '[') {
                i++;
                continue;
            }

            if (Character.isDigit(ch)) {
                int j = i + 1;
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    j++;
                }

                stack.push(s.substring(i, j));
                i = j;
                continue;
            }

            if (ch != ']') {
                int j = i + 1;
                while (j < s.length()) {
                    ch = s.charAt(j);
                    if (Character.isDigit(ch) || ch == '[' || ch == ']') {
                        break;
                    } else {
                        j++;
                    }
                }
                stack.push(s.substring(i, j));
                i = j;
                continue;
            }

            // ch == ]
            if (Character.isDigit(stack.peek().charAt(0))) {
                stack.pop();
                i++;
                continue;
            }

            String suffix = stack.pop();
            while (!stack.isEmpty() && !Character.isDigit(stack.peek().charAt(0))) {
                suffix = stack.pop() + suffix;
            }

            if (stack.isEmpty()) {
                stack.push(suffix);
            } else {
                int count = Integer.parseInt(stack.pop());
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < count; k++) {
                    sb.append(suffix);
                }
                stack.push(sb.toString());
            }

            i++;

        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new DecodeString().decodeString("3[a2[c]d]"));
    }
}
