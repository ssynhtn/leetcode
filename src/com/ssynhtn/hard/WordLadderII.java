package com.ssynhtn.hard;

import java.util.*;

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        boolean found = false;
        for (String s : wordList) {
            if (s.equals(endWord)) {
                found = true;
                break;
            }
        }

        if (!found) {
//            System.out.println("dest not in list");
            return Collections.emptyList();
        }

        Map<String, Set<String>> nbs = new HashMap<>();

        for (String s : wordList) {
            setUpNode(nbs, s);
        }
        setUpNode(nbs, beginWord);


//        for (String s : nbs.keySet()) {
//            System.out.println(s + " => " + nbs.get(s));
//        }

        return findPaths(beginWord, endWord, nbs);

    }


    private List<List<String>> findPaths(String beginWord, String endWord, Map<String, Set<String>> nbs) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, List<String>> prevMap = new HashMap<>();
        Set<String> visited = new HashSet<>();
        ArrayDeque<String> q = new ArrayDeque<>();

        dist.put(beginWord, 0);
        visited.add(beginWord);

        q.addLast(beginWord);

        boolean found = false;
        int endDist = -1;
        while (!q.isEmpty()) {
            String word = q.removeFirst();
            int d = dist.get(word);

            if (found && d >= endDist) break;

            Iterable<String> nexts = nbs.get(word);

            for (String next : nexts) {
                boolean nextVisited = visited.contains(next);
                if (nextVisited && dist.get(next) < d + 1) continue;
                dist.put(next, d + 1);
                if (!nextVisited) {
                    visited.add(next);
                    q.addLast(next);
                }
                List<String> prevList = prevMap.computeIfAbsent(next, it -> new ArrayList<>());
                prevList.add(word);

                if (!found && next.equals(endWord)) {
                    found = true;
                    endDist = d + 1;
                    break;
                }
            }
        }


        List<List<String>> res = new ArrayList<>();
        if (!found) return res;

        List<String> prefix = new LinkedList<>();
        prefix.add(endWord);

//        for (String s : prevMap.keySet()) {
//            System.out.println(s + " -> " + prevMap.get(s));
//        }

        collect(res, prefix, prevMap, endWord, false);
        return res;
    }

    private void collect(List<List<String>> res, List<String> prefix, Map<String, List<String>> prevMap, String lastWord, boolean lastIsTemplate) {
        List<String> preList = prevMap.get(lastWord);

        if (preList == null) {
            res.add(new ArrayList<>(prefix));
            return;
        }

        for (String prev : preList) {
            if (lastIsTemplate) {
                prefix.add(0, prev);
            }
            collect(res, prefix, prevMap, prev, !lastIsTemplate);
            if (lastIsTemplate) {
                prefix.remove(0);
            }
        }
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


    public static void main(String[] args) {
        System.out.println(new WordLadderII().findLadders("hit", "cog", Arrays.asList(
                "hot","dot","dog","lot","log","cog"
        )));
    }
}
