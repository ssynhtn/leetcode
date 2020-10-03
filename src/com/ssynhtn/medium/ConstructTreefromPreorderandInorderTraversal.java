package com.ssynhtn.medium;

import com.ssynhtn.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructTreefromPreorderandInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length, valToIndex);

    }

    private TreeNode buildTree(int[] preorder, int ps, int pe, int[] inorder, int is, int ie, Map<Integer, Integer> valToIndex) {
        if (ps == pe) return null;
        int rootVal = preorder[ps];
        int index = valToIndex.get(rootVal);
        TreeNode root = new TreeNode();
        root.val = rootVal;

        int pIndex = ps + 1 + index - is;
        root.left = buildTree(preorder, ps + 1, pIndex, inorder, is, index, valToIndex);
        root.right = buildTree(preorder, pIndex, pe, inorder, index + 1, ie, valToIndex);

        return root;
    }
}
