package com.ssynhtn.hard;

import java.util.*;

public class WordSearchII {
    static class Node {
        String word;
//        boolean isLast;
        Node parent;
        int chIndx;
        Node[] next = new Node[26];
        int childCount;
    }

    int[] dis = {0, 0, 1, -1};
    int[] djs = {1, -1, 0, 0};

    Node root;
    public List<String> findWords(char[][] board, String[] words) {
        root = buildTree(words);
        Set<String> res = new HashSet<>();
        int n = board.length;
        int m = board[0].length;
        Set<String> remain = new HashSet<>();
        for (String w : words) {
            remain.add(w);
        }


//        printTree(root);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Node node = root.next[board[i][j] - 'a'];
                if (node != null) {
                    collect(res, board, n, m, node, i, j);
                }
                if (root == null) break;
            }
            if (root == null) break;
        }


        return new ArrayList<>(res);
    }

    // i, j the current to consider adding, board[i][j] has char
    private void collect(Set<String> res, char[][] board, int n, int m, Node node, int i, int j) {
        if (node.word != null) {
//            System.out.println("found " + node.word);
            res.add(node.word);
            clearNode(node);
            if (root == null) return;
        }

//        System.out.println("i " + i + ", j " + j);
        char ch = board[i][j];

        // mark current as used
        board[i][j] = 0;
        for (int k = 0; k < 4; k++) {
            int nextI = i + dis[k];
            int nextJ = j + djs[k];
            if (nextI < 0 || nextI >= n) continue;
            if (nextJ < 0 || nextJ >= m) continue;
            if (board[nextI][nextJ] == 0) continue;
            Node next = node.next[board[nextI][nextJ] - 'a'];
            if (next != null) {
                collect(res, board, n, m, next, nextI, nextJ);
                if (root == null) break;
            }
        }

        board[i][j] = ch;
    }

    private void clearNode(Node node) {
        node.word = null;
        while (node.word == null && node.childCount == 0) {
            Node parent = node.parent;
            if (parent != null) {
                parent.next[node.chIndx] = null;
                parent.childCount--;
                node = parent;
            } else {
                root = null;
                break;
            }
        }

    }

    private Node buildTree(String[] words) {
        Node root = new Node();

        for (String w : words) {
            Node node = root;
            char[] chs = w.toCharArray();

            for (char ch : chs) {
                int index = ch - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Node();
                    node.next[index].chIndx = index;
//                    node.next[index].word = node.word + ch;
                    node.next[index].parent = node;
                    node.childCount++;
                }

                node = node.next[index];
            }
            node.word = w;
//            node.isLast = true;
        }

        return root;
    }

    void printTree(Node node) {
        if (node != null) {
            if (node.word != null) {
                System.out.print(node.word);
            }
            for (int i = 0; i < 26; i++) {
                Node n = node.next[i];
                if (n != null) {
//                    System.out.println("next char " + (char)(i + 'a'));
                    printTree(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new WordSearchII().findWords(new char[][]{
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}}, new String[]{
                "oath","pea","eat","rain"
        }));
//        System.out.println(new WordSearchII().findWords(new char[][]{
//                {'a'}}, new String[]{
//                "a"
//        }));
        System.out.println(new WordSearchII().findWords(new char[][]{
                {'a', 'b'},
                {'c', 'd'}}, new String[]{
                "abcd"
        }));
        System.out.println(new WordSearchII().findWords(new char[][]{
                {'a','a','a','a','a','a','a','a','a','a','a','a'},
                {'a','a','a','a','a','a','a','a','a','a','a','a'},
                {'a','a','a','a','a','a','a','a','a','a','a','a'},
                {'a','a','a','a','a','a','a','a','a','a','a','a'},
                {'a','a','a','a','a','a','a','a','a','a','a','a'},
                {'a','a','a','a','a','a','a','a','a','a','a','a'},
                {'a','a','a','a','a','a','a','a','a','a','a','a'},
                {'a','a','a','a','a','a','a','a','a','a','a','a'},
                {'a','a','a','a','a','a','a','a','a','a','a','a'},
                {'a','a','a','a','a','a','a','a','a','a','a','a'},
                {'a','a','a','a','a','a','a','a','a','a','a','a'},
                {'a','a','a','a','a','a','a','a','a','a','a','a'}
        }, new String[]{
                "a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"
        }));
    }
}
