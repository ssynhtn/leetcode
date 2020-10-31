package com.ssynhtn.card.ntree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class PreOrder {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    private void preorder(Node root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);
            for (Node c : root.children) {
                preorder(c, res);
            }


        }
    }

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    private void postorder(Node root, List<Integer> res) {
        if (root != null) {

            for (Node c : root.children) {
                postorder(c, res);
            }
            res.add(root.val);


        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.addLast(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = q.removeFirst();
                row.add(node.val);
                if (node.children != null) {
                    for (Node c : node.children) {
                        q.addLast(c);
                    }
                }
            }
            res.add(row);
        }

        return res;
    }

    public int maxDepth(Node root) {
        if (root == null) return 0;

        int max = 0;
        if (root.children != null) {
            for (Node c : root.children) {
                max = Math.max(max, maxDepth(c));
            }
        }
        return max + 1;
    }
}
