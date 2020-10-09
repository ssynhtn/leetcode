package com.ssynhtn.hard;

import com.ssynhtn.common.TreeNode;
import org.omg.PortableInterceptor.HOLDING;

public class BinaryTreeMaximumPathSum {
    public int maxPathSum(TreeNode root) {
        int[] holder = new int[2];
        findMaxPathSum(root, holder);
        return holder[0];
    }


    // root != null
    // output: holder[0]: max with at least one node, holder[1] max that use the root as one end of the path, or 0 empty path if all other choice leads to negtive values
    void findMaxPathSum(TreeNode root, int[] holder) {
        if (root.left != null) {
            findMaxPathSum(root.left, holder);
            int leftMax = holder[0];
            int leftMaxWithRoot = holder[1];

            if (root.right != null) {
                findMaxPathSum(root.right, holder);
                int rightMax = holder[0];
                int rightMaxWithRoot = holder[1];

                holder[0] = Math.max(leftMax, Math.max(rightMax, root.val + leftMaxWithRoot + rightMaxWithRoot));;
                holder[1] = Math.max(0, root.val + Math.max(leftMaxWithRoot, rightMaxWithRoot));

            } else {

                holder[0] = Math.max(leftMax, root.val + leftMaxWithRoot);
                holder[1] = Math.max(0, root.val + leftMaxWithRoot);
            }
        } else {
            if (root.right != null) {
                findMaxPathSum(root.right, holder);

                int rightMax = holder[0];
                int rightMaxWithRoot = holder[1];

                holder[0] = Math.max(rightMax, root.val + rightMaxWithRoot);
                holder[1] = Math.max(0, root.val + rightMaxWithRoot);
            } else {
                holder[0] = root.val;
                holder[1] = Math.max(0, root.val);
            }
        }
    }


}
