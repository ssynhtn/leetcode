package com.ssynhtn.medium;

import com.ssynhtn.common.TreeNode;

import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        if (root != null) {
            visit(root, nums);
        }
        return nums;
    }

    private void visit(TreeNode root, List<Integer> nums) {
        if (root.left != null) {
            visit(root.left, nums);
        }

        nums.add(root.val);
        if (root.right != null) {
            visit(root.right, nums);
        }

    }

    public List<Integer> inorderTraversalStack(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (true) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (stack.isEmpty()) break;
            node = stack.pop();
            nums.add(node.val);
            node = node.right;
        }
        return nums;
    }

    public List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> nums = new ArrayList<>();

        while (root != null) {
            if (root.left == null) {
                nums.add(root.val);
                root = root.right;
            } else {
                TreeNode node = root.left;
                while (node.right != null) {
                    node = node.right;
                }
                node.right = root;
                root = root.left;
                node.right.left = null;
            }
        }

        return nums;
    }
}
