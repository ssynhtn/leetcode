package com.ssynhtn.medium;

import java.util.ArrayDeque;

public class SimplifyPath {
    public String simplifyPath(String path) {
        if (path == null) return null;
        if (path.length() == 0) return null;
        if (path.charAt(0) != '/') return null;

        ArrayDeque<String> folders = new ArrayDeque<>();
        int pos = 1;

        String token;
        while (pos < path.length()) {
            int qos = path.indexOf('/', pos);
            if (qos == -1) {
                token = path.substring(pos);
                pos = path.length();
            } else {
                token = path.substring(pos, qos);
                pos = qos + 1;
            }

            if (token.equals(".") || token.isEmpty()) {

            } else if (token.equals("..")) {
                if (!folders.isEmpty()) {
                    folders.removeLast();
                }
            } else {
                folders.addLast(token);
            }
        }

        if (folders.isEmpty()) {
            return "/";
        } else {
            StringBuilder sb = new StringBuilder();
            while (!folders.isEmpty()) {
                sb.append('/').append(folders.removeFirst());
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(new SimplifyPath().simplifyPath("/a/./b/../../c/"));
    }
}
