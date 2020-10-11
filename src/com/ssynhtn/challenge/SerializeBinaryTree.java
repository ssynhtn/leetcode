package com.ssynhtn.challenge;

import com.ssynhtn.common.TreeNode;
import com.ssynhtn.medium.RotateImage;

public class SerializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(sb, root);
        return sb.toString();
    }

    private void serialize(StringBuilder sb, TreeNode root) {
        if (root == null) return;
        int value = root.val;
        char left = (char) (value >>> 16);
        char right = (char) (value & 0xffff);

        sb.append(left).append(right);
        serialize(sb, root.left);
        serialize(sb, root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        int[] values = new int[data.length() / 2];
        for (int i = 0, j = 0; i < values.length; i++, j += 2) {
            char left = data.charAt(j);
            char right = data.charAt(j + 1);
            int x = ((left << 16) | right);
            values[i] = x;
        }


        return deserialize(values, 0, values.length - 1);
    }

    private TreeNode deserialize(int[] values, int start, int end) {
        if (end < start) return null;
        TreeNode root = new TreeNode(values[start]);
        int rightStart = binarySearch(values, root.val, start + 1, end);
        TreeNode left = deserialize(values, start + 1, rightStart - 1);
        TreeNode right = deserialize(values, rightStart, end);
        root.left = left;
        root.right = right;
        return root;
    }

    // start, end in [0, values.len)
    // return the first index that values[index] > rootValue, or end + 1 if not found
    private int binarySearch(int[] values, int rootValue, int start, int end) {
        if (values[end] <= rootValue) return end + 1;

        while (start < end) {
            int mid = (start + end) / 2;
            if (values[mid] > rootValue) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }
}
