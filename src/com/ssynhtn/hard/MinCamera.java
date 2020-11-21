package com.ssynhtn.hard;

import com.ssynhtn.common.TreeNode;

public class MinCamera {

    public int minCameraCover(TreeNode root) {
        if (root == null) return 0;
        int[] res = compute(root);
        // System.out.println(Arrays.toString(res));
        return res[0];
    }

    /**
     a: min camera to cover tree
     b: min camera to cover tree - root
     c: min camera to cover tree, requiring root has camera

     hasLeft, hasRight

     leaf node:
     a = 1, b = 0, c = 1
     has one child node
     a: put camera on child node => child.c
     b: child.a
     c: 1 + child.b
     has two child
     a:  case 1: put camera on root, 1 + left.b + right.b
     case 2: no camera on root,
     case 1: put camera on left: left.c + right.a
     case 2: put camera on right: left.a + right.c
     b: left.a + right.a
     c: 1 + left.b + right.b
     */
    int[] compute(TreeNode root) {
        boolean hasLeft = root.left != null;
        boolean hasRight = root.right != null;

        if (!hasLeft && !hasRight) {
            return new int[]{1, 0, 1};
        }

        int[] res = new int[3];
        if (hasLeft && hasRight) {
            int[] left = compute(root.left);
            int[] right = compute(root.right);

            res[2] = 1 + left[1] + right[1];
            res[1] = Math.min(left[0] + right[0], res[2]);

            res[0] = Math.min(res[2], Math.min(left[0] + right[2], left[2] + right[0]));
        } else {
            int[] child = compute(hasLeft ? root.left : root.right);
            res[0] = child[2];
            res[1] = child[0];
            res[2] = 1 + child[1];
        }

        return res;

    }
}