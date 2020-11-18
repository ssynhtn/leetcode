package com.ssynhtn.medium;

import sun.security.acl.WorldGroupImpl;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean found = false;
        for (String s : wordList) {
            if (s.equals(endWord)) {
                found = true;
                break;
            }
        }

        if (!found) {
//            System.out.println("dest not in list");
            return 0;
        }

        Map<String, Set<String>> nbs = new HashMap<>();

        for (String s : wordList) {
            setUpNode(nbs, s);
        }
        setUpNode(nbs, beginWord);


//        for (String s : nbs.keySet()) {
//            System.out.println(s + " => " + nbs.get(s));
//        }

        return findDist2Way(beginWord, endWord, nbs);

    }

    private int findDist2Way(String beginWord, String endWord, Map<String, Set<String>> nbs) {
        Map<String, Integer> dist = new HashMap<>();
        Set<String> visited = new HashSet<>();
        ArrayDeque<String> q = new ArrayDeque<>();
        Map<String, Integer> dist2 = new HashMap<>();
        Set<String> visited2 = new HashSet<>();
        ArrayDeque<String> q2 = new ArrayDeque<>();

        dist.put(beginWord, 0);
        visited.add(beginWord);
        q.addLast(beginWord);

        dist2.put(endWord, 0);
        visited2.add(endWord);
        q2.addLast(endWord);

        String center = null;
        while (!q.isEmpty() && !q2.isEmpty()) {
            String word = q.removeFirst();
            Iterable<String> nexts = nbs.get(word);

            int d = dist.get(word);
            for (String next : nexts) {
                if (visited.contains(next)) continue;
                dist.put(next, d + 1);
                visited.add(next);
                q.addLast(next);

                if (q2.contains(next)) {
                    center = next;
                    break;
                }
            }

            if (center != null) break;

            word = q2.removeFirst();
            nexts = nbs.get(word);

            d = dist2.get(word);
            for (String next : nexts) {
                if (visited2.contains(next)) continue;
                dist2.put(next, d + 1);
                visited2.add(next);
                q2.addLast(next);

                if (q.contains(next)) {
                    center = next;
                    break;
                }
            }

            if (center != null) break;
        }

        if (center == null) return 0;

        int d = dist.getOrDefault(center, 0);
        int d2 = dist2.getOrDefault(center, 0);

        return (d + d2)/2 + 1;
    }

    private int findDist(String beginWord, String endWord, Map<String, Set<String>> nbs) {
        Map<String, Integer> dist = new HashMap<>();
        Set<String> visited = new HashSet<>();
        ArrayDeque<String> q = new ArrayDeque<>();

        dist.put(beginWord, 0);
        visited.add(beginWord);

        q.addLast(beginWord);

        while (!q.isEmpty()) {
            String word = q.removeFirst();
            Iterable<String> nexts = nbs.get(word);

            int d = dist.get(word);
            for (String next : nexts) {
                if (visited.contains(next)) continue;
                dist.put(next, d + 1);
                visited.add(next);
                q.addLast(next);

                if (next.equals(endWord)) {
                    break;
                }
            }
        }

        int d = dist.getOrDefault(endWord, 0);
        if (d == 0) return 0;
        return d/2 + 1;
    }

    private void setUpNode(Map<String, Set<String>> nbs, String s) {
        Set<String> nb1 = nbs.computeIfAbsent(s, k -> new HashSet<>());
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            char temp = chs[i];
            chs[i] = '*';
            String template = new String(chs);
            chs[i] = temp;
            Set<String> nb2 = nbs.computeIfAbsent(template, k -> new HashSet<>());
            nb1.add(template);
            nb2.add(s);
        }
    }

    private boolean isNei(String a, String b) {
        boolean found = false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (found) return false;
                found = true;
            }
        }

        return found;
    }


    public static void main(String[] args) {
        System.out.println(new WordLadder().ladderLength("ymain", "oecij", Arrays.asList(
                "ymann","yycrj","oecij","ymcnj","yzcrj","yycij","xecij","yecij","ymanj","yzcnj","ymain"
        )));
    }
}
