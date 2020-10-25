package com.ssynhtn.card.tree;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PostOrderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;



        return res;

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
