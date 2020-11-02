package com.ssynhtn.card.bst;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayDeque;

public class BSTIterator {

    // invariant:
    // stack is made up of path from root to current smallest node
    private ArrayDeque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.peek();
        int res = node.val;

        if (node.right != null) {
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        } else {
            stack.pop();
            while (!stack.isEmpty() && stack.peek().right == node) {
                node = stack.pop();
            }
        }

        return res;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}