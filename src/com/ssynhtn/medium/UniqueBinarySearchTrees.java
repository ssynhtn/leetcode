package com.ssynhtn.medium;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int[] nums = new int[n + 1];
        nums[0] = 1;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int x = 0; x < i / 2; x++) {
                sum += nums[x] * nums[i - 1 - x] * 2;
                if (i == n) {
                    System.out.println(nums[x] + " * " + nums[i - 1 - x] + " * 2");
                }
            }
            if ((i & 1) == 1) {
                int x = (i >> 1);
                sum += nums[x] * nums[x];

                if (i == n) {
                    System.out.println(nums[x] + " * " + nums[x]);
                }
            }
            nums[i] = sum;
        }
        for (int i = 0; i <= n; i++) {
            System.out.println(nums[i]);
        }
        return nums[n];
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return Collections.emptyList();
        return generateTrees(1, n);

    }

    private List<TreeNode> generateTrees(int small, int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n == 0) {
            res.add(null);
            return res;
        } else if (n == 1) {
            res.add(new TreeNode(small));
            return res;
        }

        // small,... small + n - 1
        int large = small + n - 1;
        for (int rootVal = small; rootVal <= large; rootVal++) {
            List<TreeNode> left = generateTrees(small, rootVal - small);
            List<TreeNode> right = generateTrees(rootVal + 1, large - rootVal);
            for (TreeNode leftNode : left) {
                TreeNode root = new TreeNode(rootVal);
                root.left = leftNode;
                root.right = right.get(0);
                res.add(root);
                for (int j = 1; j < right.size(); j++) {
                    root = new TreeNode(rootVal);
                    root.left = clone(leftNode);
                    root.right = clone(right.get(j));
                    res.add(root);
                }
            }
        }



        return res;
    }

    private TreeNode clone(TreeNode root) {
        if (root == null) return null;
        TreeNode rootClone = new TreeNode(root.val);
        rootClone.left = clone(root.left);
        rootClone.right = clone(root.right);
        return rootClone;
    }

    public static void main(String[] args) {
        System.out.println(new UniqueBinarySearchTrees().numTrees(5));
    }

}
