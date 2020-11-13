package com.ssynhtn.hard;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class RemoveMinParen {

//    string[] extras
//        int[] ls
//    3 5 8 6 5 4
//        int[] rs
//    2 7 9 4 7 2
//        int[] rExtra
//    0 1 1 0 0 0
//        int[] lExtra
//    0 0 0 0 0 2

//    total = sumSup
//            removed
//    移除
//    max(sup[i] - removed, 0) ~ min(total-removed, tokenSize[i])
    public List<String> removeInvalidParentheses(String s) {
//        Input: "(a)())()"
//        Output: ["(a)()()", "(a())()"]
//          a
//        1 0 1 1
//        0 1 2 1

//        0 0 1 0
//
//        (a)()()
//        (a())()

        char[] chs = s.toCharArray();
        int i = 0;
        final int n = chs.length;

        List<Integer> ls = new ArrayList<>();
        List<String> as = new ArrayList<>();
        List<Integer> rs = new ArrayList<>();

        int j;
        while (i < n) {
            j = i;
            while (j < n && chs[j] == '(') {
                j++;
            }
            ls.add(j-i);
            i = j;

            while (j < n && chs[j] != '(' && chs[j] != ')') {
                j++;
            }
            as.add(new String(chs, i, j-i));
            i = j;

            while (j < n && chs[j] == ')') {
                j++;
            }
            rs.add(j-i);
            i = j;

        }

        int tokenSize = ls.size();
        int[] sups2 = new int[tokenSize];
//        int[] subs2 = new int[tokenSize];

        int lastPos = -1;
        int diff = 0;
        int diff2 = 0;
        int firstPartSize = 0;
        int total1 = 0;
        for (i = 0; i < tokenSize; i++) {
            diff += rs.get(i) - ls.get(i);
            diff2 += rs.get(i) - ls.get(i);
            sups2[i] = diff2;
            firstPartSize += ls.get(i) + rs.get(i) + as.get(i).length() - Math.max(0, diff);

            if (diff >= 0) {
                total1 += diff;
                diff = 0;
                lastPos = i;
            }
        }

        int firstPos = tokenSize;
        int total2 = 0;
        diff = 0;
        diff2 = 0;
        for (i = tokenSize - 1; i > lastPos; i--) {
            diff += ls.get(i) - rs.get(i);
            diff2 += ls.get(i) - rs.get(i);
            sups2[i] = diff2;
            if (diff >= 0) {
                total2 += diff;
                diff = 0;
                firstPos = i;
            }
        }

//        firstPos = Math.max(lastPos + 1, firstPos);

        int total = total1 + total2;
        final int m = n - total;
        char[] buffer = new char[m];

        i = firstPartSize;
        for (j = lastPos + 1; j < firstPos; j++) {
            for (int k = 0; k < ls.get(j); k++) {
                buffer[i++] = '(';
            }
            for (int k = 0; k < as.get(j).length(); k++) {
                buffer[i++] = as.get(j).charAt(k);
            }
            for (int k = 0; k < rs.get(j); k++) {
                buffer[i++] = ')';
            }
        }

//        System.out.println("input " + s);
//        System.out.println("ls " + ls);
//        System.out.println("as " + as);
//        System.out.println("rs " + rs);
//        System.out.println("sups2 " + Arrays.toString(sups2));
//        System.out.println("subs2 " + Arrays.toString(subs2));
//        System.out.println("total1 " + total1);
//        System.out.println("total2 " + total2);
//        System.out.println("lastPos " + lastPos);
//        System.out.println("firstPos " + firstPos);
//        System.out.println(Arrays.toString(buffer));
//        System.out.println("buffer size " + m);

        List<String> res = new ArrayList<>();
        collectLeft(res, ls, as, rs, sups2, lastPos, firstPos, total1, total2, 0, 0, buffer, 0);


        return res;
    }

    private void collectLeft(List<String> res, List<Integer> ls, List<String> as, List<Integer> rs,
                             int[] sups, int lastPos, int firstPos, int total1, int total2, int i, int removed,
                             char[] buffer, int bi) {

        if (i > lastPos) {
            collectRight(res, ls, as, rs, sups, firstPos, total2, ls.size() - 1, 0, buffer, buffer.length - 1);
            return;
        }

        int l = ls.get(i);
        String a = as.get(i);
        int r = rs.get(i);

        for (int _i = 0; _i < l; _i++) {
            buffer[bi++] = '(';
        }
        for (int _i = 0; _i < a.length(); _i++) {
            buffer[bi++] = a.charAt(_i);
        }

        //    max(sup[i] - removed, 0) ~ min(total-removed, rs[i])
        // how many to remove

        int min = Math.max(sups[i] - removed, 0);
        int max = Math.min(total1 - removed, r);

        int least = r - max;
        for (int _i = 0; _i < least; _i++) {
            buffer[bi++] = ')';
        }

        int extraRemoved = max;
        while (extraRemoved >= min) {
            collectLeft(res, ls, as, rs, sups, lastPos, firstPos, total1, total2, i + 1, removed + extraRemoved, buffer, bi);
            if (extraRemoved == min) break;
//            System.out.println("bi " + bi + ", extraRemoved " + extraRemoved + ", min " + min);
            buffer[bi++] = ')';
            extraRemoved--;
        }


    }

    private void collectRight(List<String> res, List<Integer> ls, List<String> as, List<Integer> rs,
                              int[] subs, int firstPos, int total2, int i, int removed, char[] buffer, int bi) {

        if (i < firstPos) {
            res.add(new String(buffer));
//            System.out.println("i " + i + ", firstPos " + firstPos + ", adding " + res.get(res.size() - 1));
            return;
        }

//        System.out.println("i " + i + ", firstPos " + firstPos);

        int l = ls.get(i);
        String a = as.get(i);
        int r = rs.get(i);


        for (int _i = 0; _i < r; _i++) {
            buffer[bi--] = ')';
        }
        for (int _i = a.length() - 1; _i >= 0; _i--) {
            buffer[bi--] = a.charAt(_i);
        }


        //    max(sub[i] - removed, 0) ~ min(total-removed, rs[i])
        // how many to remove

        int min = Math.max(subs[i] - removed, 0);
        int max = Math.min(total2 - removed, l);

//        System.out.println("max " + max + ", min " + min + ", total2 " + total2 + ", removed " + removed + ", l"+ l);

        int least = l - max;
        for (int _i = 0; _i < least; _i++) {
            buffer[bi--] = '(';
        }

        int extraRemoved = max;
        while (extraRemoved >= min) {
            collectRight(res, ls, as, rs, subs, firstPos, total2, i - 1, removed + extraRemoved, buffer, bi);
            if (extraRemoved == min) break;
            buffer[bi--] = '(';
            extraRemoved--;
        }

    }

    public static void main(String[] args) {
        List<String> res = new RemoveMinParen().removeInvalidParentheses(")(");
        System.out.println("res " + res + ", size " + res.size());
    }

}
