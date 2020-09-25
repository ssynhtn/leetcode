package com.ssynhtn.medium;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DecodeString {
    public String decode(String s, int k) {
        long total = 0;
        int i = 0;
        char ch;
        while (i < s.length()) {
            ch = s.charAt(i);
             if (Character.isDigit(ch)) {
                 total = total * (ch - '0');
             } else {
                 total++;
             }
            i++;
        }

        i = s.length() - 1;
        long target = k;
        while (true) {
            ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                total = total / (ch - '0');
                target = (target - 1) % total + 1;
            } else {
                if (total == target) {
                    return ch + "";
                }
                total--;
            }
            i--;
        }

    }
    public String decodeAtIndex(String s, int K) {
        long index = K - 1;
        if (index == 0) return s.substring(0, 1);
        List<String> tokens = new ArrayList<>();
        List<Long> lengths = new ArrayList<>();

        int i = 0;
        while (i < s.length() && !Character.isDigit(s.charAt(i))) {
            i++;
        }

        tokens.add(s.substring(0, i));
        lengths.add((long) i);

        char ch;
        while (i < s.length()) {
            ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                tokens.add(ch + "");
                long len = lengths.get(lengths.size() - 1) * (ch - '0');
                lengths.add(len);
                i++;
                if (len > index) {
                    break;
                }
            } else {
                int j = i + 1;
                while (j < s.length() && !Character.isDigit(s.charAt(j))) {
                    j++;
                }

                String token = s.substring(i, j);
                tokens.add(token);
                long len = lengths.get(lengths.size() - 1) + token.length();
                lengths.add(len);
                i = j;
                if (len > index) {
                    break;
                }

            }
        }

        while (true){
            int j = lengths.size() - 1;
            while (j >= 0 && lengths.get(j) > index) {
                j--;
            }

            // [0] > index, j == -1
            // [0] < index, index in [j], [j + 1]


            String token = tokens.get(j + 1);
            if (Character.isDigit(token.charAt(0))) {
                index = index % (lengths.get(j)) ;
            } else {
                int charIndex = (int) (index - (j >= 0 ? lengths.get(j) : 0));
                return token.substring(charIndex, charIndex + 1);
            }



        }


    }

    public static void main(String[] args) {
        System.out.println(new DecodeString().decode("a23", 6));
    }
}
