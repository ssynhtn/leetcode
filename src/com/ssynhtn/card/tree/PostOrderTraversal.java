package com.ssynhtn.card.tree;

import com.ssynhtn.common.TreeNode;
import jdk.nashorn.internal.ir.TernaryNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class PostOrderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        ArrayDeque<Integer> pcStack = new ArrayDeque<>();

        stack.addLast(root);
        pcStack.addLast(0);

        while (!stack.isEmpty()) {
            if (pcStack.peekLast() == 0) {
                pcStack.removeLast();
                pcStack.addLast(1);

                TreeNode node = stack.peekLast();
                if (node.left != null) {
                    stack.addLast(node.left);
                    pcStack.addLast(0);
                }
            } else if (pcStack.peekLast() == 1) {
                pcStack.removeLast();
                pcStack.addLast(2);

                TreeNode node = stack.peekLast();
                if (node.right != null) {
                    stack.addLast(node.right);
                    pcStack.addLast(0);
                }
            } else {
                pcStack.removeLast();
                res.add(stack.removeLast().val);
            }
        }


        return res;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        System.out.println(new PostOrderTraversal().postorderTraversal(root));
    }


    public List<Integer> postorderTraversalRec(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        postOrder(res, root);

        return res;

    }

    private void postOrder(List<Integer> res, TreeNode root) {
        if (root.left != null) {
            postOrder(res, root.left);
        }
        if (root.right != null) {
            postOrder(res, root.right);
        }

        res.add(root.val);
    }
}
