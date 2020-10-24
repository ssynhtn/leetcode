package com.ssynhtn.mock;

import java.util.*;

public class BraceExpansion {
    public List<String> braceExpansionII(String expression) {
        Set<String> prefix = new HashSet<>();
        prefix.add("");
        Set[] setHolder = new Set[]{prefix};

        int used = consumeExpression(setHolder, 0, expression);
        if (used < expression.length()) {
            System.out.println("warning, dangling tail " + expression.substring(used));
        }

        List<String> res = new ArrayList<>(setHolder[0]);
        Collections.sort(res);
        return res;
    }

    private int consumeExpression(Set[] prefixHolder, int start, String expression) {

        int i = start;
        int n = expression.length();
        while (i < n) {
            char ch = expression.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                Set<String> rep = new HashSet<>();
                for (Object s : prefixHolder[0]) {
                    rep.add((String) s + ch);
                }
                prefixHolder[0] = rep;
                i++;
            } else if (ch == '{') {
                i++;
                List<String> temp = new ArrayList<>();
                while (expression.charAt(i) != '}') {
                    Set<String> nextPrefix = new HashSet<>();
                    nextPrefix.add("");
                    Set[] nextHolder = new Set[]{nextPrefix};
                    int used = consumeExpression(nextHolder, i, expression);
//                    System.out.println(i + ":" + (i + used) + " -> " + nextPrefix);
                    temp.addAll(nextHolder[0]);
                    i += used;
                    if (expression.charAt(i) == ',') {
                        i++;
                    }
                }

                i++;
                Set<String> rep = new HashSet<>();
                Set<String> prefix = prefixHolder[0];
                for (String a : prefix) {
                    for (String b : temp) {
                        rep.add(a + b);
                    }
                }
                prefixHolder[0] = rep;

            } else {
                break;
            }
        }

        return i - start;
    }

    public static void main(String[] args) {
        System.out.println(new BraceExpansion().braceExpansionII("{{a,z},a{b,c},{ab,z}}"));
    }
}
