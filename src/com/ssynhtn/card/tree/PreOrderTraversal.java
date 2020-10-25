package com.ssynhtn.card.tree;

import com.ssynhtn.common.TreeNode;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class PreOrderTraversal {
    public List<Integer> preorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode node = root;
        while (node != null) {
            res.add(node.val);
            if (node.left == null) {
                node = node.right;
            } else {
                if (node.right != null) {
                    TreeNode pre = node.left;
                    while (pre.right != null) {
                        pre = pre.right;
                    }
                    pre.right = node.right;
                }
                node = node.left;
            }
        }
        return res;
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if (root == null) return res;

        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);

        while (!q.isEmpty()) {
            TreeNode node = q.removeLast();
            res.add(node.val);
            if (node.right != null) {
                q.addLast(node.right);
            }
            if (node.left != null) {
                q.addLast(node.left);
            }
        }

        return res;
    }
}
