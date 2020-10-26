package com.ssynhtn.card.tree;

import com.ssynhtn.common.TreeNode;

import java.util.HashMap;
import java.util.Map;


public class ConstructFromPostOrderInOrder {
    Map<Integer, Integer> indexMap;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    // right >= left
    private TreeNode buildTree(int[] inorder, int left, int right, int[] postorder, int left2, int right2) {
        if (left == right) return null;
        if (right == left + 1) {
            return new TreeNode(inorder[left]);
        }

        int rootVal = postorder[right2 - 1];
        TreeNode root = new TreeNode(rootVal);

//        int index = -1;
//        for (int i = left; i < right; i++) {
//            if (inorder[i] == rootVal) {
//                index = i;
//                break;
//            }
//        }

        int index = indexMap.get(rootVal);

        int size = index - left;
        int index2 = left2 + size;
        root.left = buildTree(inorder, left, index, postorder, left2, index2);
        root.right = buildTree(inorder, index + 1, right, postorder, index2, right2 - 1);
        return root;
    }
}
