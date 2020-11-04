package com.ssynhtn.medium;

import com.ssynhtn.common.TreeNode;

public class SumRootToLeaf {
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        return sumNumbers(0, root);
    }

    // root != null
    private int sumNumbers(int prefix, TreeNode root) {
        prefix = prefix * 10 + root.val;
        if (root.left == null && root.right == null) {
            return prefix;
        }

        int sum = 0;
        if (root.left != null) {
            sum += sumNumbers(prefix, root.left);
        }
        if (root.right != null) {
            sum += sumNumbers(prefix, root.right);
        }
        return sum;
    }

}
