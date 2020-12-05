package com.ssynhtn.medium;

import java.util.Arrays;
import java.util.Comparator;

class FindLongestUncommonSubString {
    public int findLUSlength(String[] strs) {
        int j = 0;
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int n = a.length();
                int m = b.length();
                if (n != m) {
                    return m - n;
                }

                return b.compareTo(a);
            }
        });

        int i = 0;
        final int n = strs.length;
        while (i < n) {
            String s = strs[i];
            if (i + 1 == n || !strs[i+1].equals(s)) {
                boolean isSub = false;
                for (int l = 0; l < j; l++) {
                    if (isSub(s, strs[l])) {
                        isSub = true;
//                        System.out.println("putting " + s + " at " + j);
                        strs[j++] = s;
                        break;
                    }
                }

                if (!isSub) return s.length();
                i++;

            } else {
                strs[j++] = s;
                i++;
                while (i < n && strs[i].equals(s)) {
                    i++;
                }
            }
        }

        return -1;
    }

    // check a is subseq of b
    private boolean isSub(String a, String b) {
        char[] chs = a.toCharArray();
        char[] bhs = b.toCharArray();
        int n = chs.length;
        int m = bhs.length;
        int i = 0;
        int j = 0;
        while (i < n && j < m && n - i <= m - j) {
            if (chs[i] == bhs[j]) {
                i++;
                j++;
            } else {
                j++;
            }
        }

        return i == n;
    }

    public static void main(String[] args) {
        System.out.println(new FindLongestUncommonSubString().findLUSlength(new String[]{"aabbcc", "aabbcc","cb","abc"}));


    }

}