package com.ssynhtn.hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StreamChecker {
    static class Node {
        boolean isWord;

        Node[] children = new Node[26];

    }

    private Node root;
    int maxLen;
    List<Character> chs;
    public StreamChecker(String[] words) {
        root = buildTrie(words);
        chs = new LinkedList<>();
    }

    public boolean query(char letter) {
        if (maxLen == 0) return false;
        chs.add(0, letter);
        if (chs.size() > maxLen) {
            chs.remove(chs.size() - 1);
        }

        return checkWord(root, chs);
    }

    Node buildTrie(String[] words) {
        Node root = new Node();

        for (String w : words) {
            addWord(root, w);
            maxLen = Math.max(maxLen, w.length());
        }

        return root;

    }

    private void addWord(Node root, String w) {
        char[] chs = w.toCharArray();
        for (int i = chs.length - 1; i >= 0; i--) {
            char ch = chs[i];
            Node node = root.children[ch - 'a'];
            if (node == null) {
                node = new Node();
                root.children[ch - 'a'] = node;
            }
            root = node;
        }
        root.isWord = true;
    }

    private boolean checkWord(Node root, List<Character> chars) {
        for (Character ch : chars) {
            if ((root = root.children[ch - 'a']) == null) {
                return false;
            }
            if (root.isWord) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        StreamChecker checker = new StreamChecker(new String[]{"cd", "f", "kl"});
        for (char ch : "abcdefghijkl".toCharArray()) {
            System.out.println(checker.query(ch));
        }
    }
}
