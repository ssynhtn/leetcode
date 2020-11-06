package com.ssynhtn.hard;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayDeque;

public class RecoverTree {
    int n;
    int i = 0;
    char[] chs;
    public TreeNode recoverFromPreorder(String S) {
        if (S == null || S.length() == 0) return null;
        chs = S.toCharArray();
        n = chs.length;
        
        TreeNode root = nextNode();
        ArrayDeque<TreeNode> path = new ArrayDeque<>();
        path.addLast(root);
        int depth = 0;

        while (i < n) {
            int nextDepth = skipDepth();
            TreeNode nextNode = nextNode();

            if (nextDepth > depth) {
                path.peekLast().left = nextNode;
                path.addLast(nextNode);
                depth = nextDepth;
            } else {
                int removeCount = depth - nextDepth + 1;
                while (removeCount > 0) {
                    path.removeLast();
                    removeCount--;
                }
                path.peekLast().right = nextNode;
                path.addLast(nextNode);
                depth = nextDepth;
            }
        }
        
        return root;
        
        
    }
    
    // i at digit
    TreeNode nextNode() {
        int x = 0;
        while (i < n && chs[i] != '-') {
            x = x * 10 + chs[i] - '0';
            i++;
        }
        
        return new TreeNode(x);
    }
    
    // i at -
    int skipDepth() {
        int oldI = i;
        while (i < n && chs[i] == '-') {
            i++;
        }
        return i - oldI;
    }

    public static void main(String[] args) {
        System.out.println(new RecoverTree().recoverFromPreorder("1-2--3--4-5--6--7"));
    }
}