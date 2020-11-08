package com.ssynhtn.medium;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayDeque;

public class TreeWidth {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int maxWidth = 0;
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        root.val = 0;
        q.addLast(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            int width = 0;
            int nullCount = 0;
            boolean firstInserted = false;
            System.out.println("start row");
            for (int i = 0; i < size; i++) {
                TreeNode node = q.removeFirst();
                width += node.val + 1;
                System.out.print("node add with " + (node.val + 1) + ", ");
                
                /*
                if left not null, add left, set val to nullCount + 1, set firstInserted to true
                if left is null
                    
                */
                
             
                if (node.left != null) {
                    node.left.val = firstInserted ? nullCount + node.val * 2 : 0;
                    System.out.println("nCount " + nullCount + ", node.val " + node.val + ", setting left.val " + node.left.val);
                    q.addLast(node.left);
                    firstInserted = true;
                    nullCount = 0;
                } else {
                    nullCount += node.val * 2 + 1;
                }
                
                if (node.right != null) {
                    node.right.val = firstInserted ? nullCount : 0;
                    q.addLast(node.right);
                    firstInserted = true;
                    nullCount = 0;
                } else {
                    nullCount++;
                }
                
            }
            System.out.println(", width " + width);
            
            maxWidth = Math.max(maxWidth, width);
        }
        
        return maxWidth;
        
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        System.out.println(new TreeWidth().widthOfBinaryTree(root));
    }
}