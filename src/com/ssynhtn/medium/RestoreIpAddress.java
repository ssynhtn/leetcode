package com.ssynhtn.medium;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> collect = new ArrayList();

        List<String> tokens = new ArrayList();
        find(s, 0, tokens, collect);

        return collect;
    }

    void find(String s, int pos, List<String> tokens, List<String> collect) {
        if (tokens.size() == 4) {
            if (pos == s.length()) {
                collect.add(join(tokens));
            }

            return;
        }

        int remainLen = s.length() - pos;
        int minLength = 4 - tokens.size();
        if (remainLen < minLength) {
            return;
        }
        int maxLength = minLength * 3;
        if (remainLen > maxLength) return;

        char ch = s.charAt(pos);

        // single digit
        tokens.add(ch + "");
        find(s, pos + 1, tokens, collect);
        tokens.remove(tokens.size() - 1);

        // double digit
        if (ch == '0') return;
        if (remainLen == 1) return;

        tokens.add(s.substring(pos, pos + 2));
        find(s, pos + 2, tokens, collect);
        tokens.remove(tokens.size() - 1);

        // xxx
        if (remainLen == 2) return;
        if (ch >= '3') return;
        String token = s.substring(pos, pos + 3);
        int num = Integer.parseInt(token);
        if (num > 255) return;

        tokens.add(token);
        find(s, pos + 3, tokens, collect);
        tokens.remove(tokens.size() - 1);



    }

    String join(List<String> tokens) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.size(); i++) {
            if (i > 0) {
                sb.append(".");
            }
            sb.append(tokens.get(i));
        }

        return sb.toString();
    }
}
