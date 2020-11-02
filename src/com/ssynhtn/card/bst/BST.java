package com.ssynhtn.card.bst;

import com.ssynhtn.common.TreeNode;

public class BST {
    public TreeNode searchBSTRec(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        if (val < root.val) return searchBSTRec(root.left, val);
        return searchBSTRec(root.right, val);
    }

    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) return root;
            if (val < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return null;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
            return root;
        }
        root.right = insertIntoBST(root.right, val);
        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode parent = null;
        TreeNode node = root;
        while (node != null) {
            if (node.val == key) break;
            parent = node;
            if (key < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (node == null) return root;

        if (node.left == null && node.right == null) {
            if (parent == null) return null;
            if (parent.left == node) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (node.left == null || node.right == null) {
            TreeNode child = node.left == null ? node.right : node.left;
            if (parent == null) return child;
            if (parent.left == node) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            TreeNode prev = null;
            TreeNode right = node.left;
            while (right.right != null) {
                prev = right;
                right = right.right;
            }

            node.val = right.val;
            if (prev != null) {
                prev.right = right.left;
            } else {
                node.left = right.left;
            }
        }

        return root;


    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        int a = Math.min(p.val, q.val);
        int b = Math.max(p.val, q.val);

        while (true) {
            if (a <= root.val && root.val <= b) {
                return root;
            }

            if (a > root.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

    }
}
