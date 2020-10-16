package com.ssynhtn.medium;

import com.ssynhtn.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class HouseRobberIII {
    public int robWithCache(TreeNode root) {
        if (root == null) return 0;
        Map<TreeNode, Integer> cache = new HashMap<>();
        return robWithCache(root, cache);


    }

    private int robWithCache(TreeNode root, Map<TreeNode, Integer> cache) {
        if (root == null) return 0;
        Integer amount = cache.get(root);
        if (amount != null) return amount;

        int total = 0;
        total += root.val;
        if (root.left != null) {
            total += robWithCache(root.left.left, cache) + robWithCache(root.left.right, cache);
        }
        if (root.right != null) {
            total += robWithCache(root.right.left, cache) + robWithCache(root.right.right, cache);
        }

        int total2 = robWithCache(root.left, cache) + robWithCache(root.right, cache);
        int res = Math.max(total, total2);
        cache.put(root, res);
        return res;
    }

    // return rob max, if root is not null, modifies root.val to be if skip root max
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int left = rob(root.left);
        int right = rob(root.right);
        int skipRoot = left + right;
        int withRoot = root.val + (root.left != null ? root.left.val : 0) + (root.right != null ? root.right.val : 0);
        root.val = skipRoot;
        return Math.max(withRoot, skipRoot);
    }
}
