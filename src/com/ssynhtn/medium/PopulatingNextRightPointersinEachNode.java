package com.ssynhtn.medium;

import com.ssynhtn.common.Node;

public class PopulatingNextRightPointersinEachNode {
    public Node connect(Node root) {
        if (root != null) {
            connect(root.left);
            connect(root.right);

            Node left = root.left;
            Node right = root.right;

            while (left != null) {
                left.next = right;
                left = left.right;
                right = right.left;
            }
        }
        return root;
    }
}
