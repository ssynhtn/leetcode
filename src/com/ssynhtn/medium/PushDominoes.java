package com.ssynhtn.medium;

import java.util.*;

public class PushDominoes {
    public String pushDominoes(String dominoes) {
        char[] chs = dominoes.toCharArray();
        Queue<Integer> ls = new ArrayDeque<>();
        Queue<Integer> rs = new ArrayDeque<>();

        int i = 0;
        int len = dominoes.length();
        while (i < len && chs[i] == '.') {
            i++;
        }
        if (i == len) {
            return dominoes;
        }



        while (true) {
            // i at start of none .
            if(i != 0) {
                if (chs[i] == 'L') {
                    ls.add(i);
                } else {
                    rs.add(i);
                }
            }

            int j = i + 1;
            while (j < len && chs[j] != '.') {
                j++;
            }

            if (j == len) break;
            // found .
            if (j - i > 1 || i == 0) {
                if (chs[j - 1] == 'L') {
                    ls.add(j - 1);
                } else {
                    rs.add(j - 1);
                }
            }

            i = j;
            while (i < len && chs[i] == '.') {
                i++;
            }

            if (i == len) break;
        }

//        for (int i = 0; i < dominoes.length(); i++) {
//            if (dominoes.charAt(i) == 'L') {
//                ls.add(i);
//            } else if (dominoes.charAt(i) == 'R') {
//                rs.add(i);
//            }
//        }

//        System.out.println("ls " + ls);
//        System.out.println("rs " + rs);

        while (!ls.isEmpty() || !rs.isEmpty()) {
            int n = ls.size();
            int m = rs.size();

            for (i = 0; i < n; i++) {
                int index = ls.remove();
                if (index == 0) continue;
                if (chs[index - 1] != '.') continue;
                if (index == 1 || chs[index - 2] != 'R') {
                    ls.add(index - 1);
                    chs[index - 1] = 'L';


                    if (index >= 3 && chs[index - 2] == '.' && chs[index - 3] == 'R') {
                        chs[index - 2] = 'R';
                        rs.add(index - 2);
                    }
                }
            }

            for (i = 0; i < m; i++) {
                int index = rs.remove();
                if (index == dominoes.length() - 1) continue;
                if (chs[index + 1] != '.') continue;
                if (index == dominoes.length() - 2) {
                    rs.add(index + 1);
                    chs[index + 1] = 'R';

                } else {
                    if (chs[index + 2] != 'L') {
                        rs.add(index + 1);
                        chs[index + 1] = 'R';
                    }
                }
            }


        }

        return new String(chs);
    }

    public static void main(String[] args) {
        System.out.println(new PushDominoes().pushDominoes(".L.R...LR..L.."));
    }
}
