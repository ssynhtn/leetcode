package com.ssynhtn.card.tree;

import com.ssynhtn.common.TreeNode;
import org.omg.CORBA.IRObject;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestorBFS(TreeNode root, TreeNode p, TreeNode q) {
        // todo
        return null;

    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> one = new ArrayList<>();
        findPath(root, p, one);
        List<TreeNode> two = new ArrayList<>();
        findPath(root, q, two);

        int n = Math.min(one.size(), two.size());
        for (int i = 0; i < n; i++) {
            if (one.get(i) != two.get(i)) {
                return one.get(i - 1);
            }
        }

        // find path from root to p and q, and find the last common node
        return one.get(n - 1);
    }

    private boolean findPath(TreeNode root, TreeNode p, List<TreeNode> path) {
        path.add(root);
        if (root == p) return true;
        if (root.left != null && findPath(root.left, p, path)) {
            return true;
        }
        if (root.right != null && findPath(root.right, p, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }
}
