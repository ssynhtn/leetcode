package com.ssynhtn.easy;

import com.ssynhtn.hard.RemoveDuplicateLetters;

import java.util.ArrayList;
import java.util.List;

public class RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        char last = 0;

        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (last == ch) {
                sb.deleteCharAt(sb.length() - 1);
                last = sb.length() == 0 ? 0 : sb.charAt(sb.length() - 1);
            } else {
                sb.append(ch);
                last = ch;
            }
        }

        return sb.toString();
    }

    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        char last = 0;

        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch != last) {
                sb.append(ch);
                last = ch;
                count = 1;
            } else {
                if (count == k - 1) {
                    sb.delete(sb.length() - k + 1, sb.length());
                    if (sb.length() == 0) {
                        last = 0;
                        count = 0;
                    } else {
                        last = sb.charAt(sb.length() - 1);
                        count = 1;
                        int j = sb.length() - 2;
                        while (j >= 0 && sb.charAt(j) == last) {
                            j--;
                            count++;
                        }
                    }
                } else {
                    sb.append(ch);
                    count++;
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new RemoveAllAdjacentDuplicatesInString().removeDuplicates("deeedbbcccbdaa", 3));
    }
}
