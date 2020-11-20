package com.ssynhtn.easy;

import java.util.ArrayDeque;

public class DecodeString {
    public String decodeString2(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        StringBuilder sb = new StringBuilder();
        decodeRec(sb, chs, 0, n);

        return sb.toString();
    }


//    recurse: // parse for the longest valid from i, append to buffer
//    int n = buffer.length
//    while (peek != ']')
//    char: append, i++
//    n:
//    skip '['
//    recurse
//    repeat result n times
//    skip ']'
//
//    recurse
    private int decodeRec(StringBuilder sb, char[] chs, int i, int n) {
        if (i >= n) return i;

        char ch;
        while (i < n && (ch = chs[i]) != ']') {
            if (Character.isAlphabetic(ch)) {
                sb.append(ch);
                i++;
            } else {
                if (Character.isDigit(ch)) {
                    int count = ch - '0';
                    i++;
                    while (i < n && Character.isDigit(chs[i])) {
                        count = count * 10 + chs[i] - '0';
                        i++;
                    }

                    i++;
                    int len = sb.length();
                    int j = decodeRec(sb, chs, i, n);
                    int len2 = sb.length();
                    count--;
                    CharSequence part = sb.subSequence(len, len2);
                    while (count > 0) {
                        sb.append(part);
                        count--;
                    }
                    i = j + 1;  // skip ]

                } else {
                    throw new RuntimeException("not alpha num");
                }
            }
        }

        return i;
    }

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
        System.out.println(new DecodeString().decodeString2("3[a2[c]d]"));
    }
}
