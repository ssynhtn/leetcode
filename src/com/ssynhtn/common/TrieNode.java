package com.ssynhtn.common;

public class TrieNode {
    public boolean isWord;
    public String word;
    public TrieNode[] children = new TrieNode[26];

    public static TrieNode buildTree(Iterable<String> words) {
        TrieNode root = new TrieNode();

        for (String w : words) {
            addWord(root, w);
        }

        return root;

    }


    private static void addWord(TrieNode root, String w) {
        char[] chs = w.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            char ch = chs[i];
            TrieNode trieNode = root.children[ch - 'a'];
            if (trieNode == null) {
                trieNode = new TrieNode();
                trieNode.word = new String(chs, 0, i + 1);
                root.children[ch - 'a'] = trieNode;
            }
            root = trieNode;
        }
        root.isWord = true;
    }

    public void print() {
        if (isWord) {
            System.out.println("word " + word);
        } else {
            System.out.println("prefix " + word);
        }
        for (TrieNode node : children) {
            if (node != null) {
                node.print();
            }
        }
    }

}
