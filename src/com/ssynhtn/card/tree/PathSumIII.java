package com.ssynhtn.card.tree;

import com.ssynhtn.common.TreeNode;

public class PathSumIII {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        int count = 0;
        count += pathSum(root.left, sum);
        count += pathSum(root.right, sum);
        if (root.val == sum) count++;
        count += pathSumStaringAtRoot(root.left, sum - root.val);
        count += pathSumStaringAtRoot(root.right, sum - root.val);
        return count;
    }

    public int pathSumStaringAtRoot(TreeNode root, int sum) {
        if (root == null) return 0;
        int count = 0;
        if (root.val == sum) {
            count++;
        }

        if (root.left != null) {
            count += pathSumStaringAtRoot(root.left, sum - root.val);
        }
        if (root.right != null) {
            count += pathSumStaringAtRoot(root.right, sum - root.val);
        }
        return count;
    }

}
