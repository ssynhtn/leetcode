package com.ssynhtn.hard;

import com.ssynhtn.common.TreeNode;
import com.ssynhtn.medium.NumMatrix;
import com.sun.xml.internal.xsom.XSTerm;

import javax.print.DocFlavor;
import java.util.ArrayDeque;
import java.util.Arrays;

public class NumOfWaysBinarySearchTree {

    private static final int MOD = 1000000007;
    public int numOfWays(int[] nums) {
        if (nums.length == 0) return 0;

        TreeNode treeNode = buildBinarySearchTree(nums);
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.addLast(treeNode);

        int count = recurse(q);
        if (count == 0) {
            return MOD  - 1;
        }
        return count - 1;
    }

    private int recurse(ArrayDeque<TreeNode> q) {
        if (q.isEmpty()) return 1;
        int count = 0;
        int size = q.size();
        for (int i = 0; i < size; i++) {
            TreeNode a = q.removeFirst();
            if (a.left != null) {
                q.addLast(a.left);
            }
            if (a.right != null) {
                q.addLast(a.right);
            }
            count = (count + recurse(q)) % MOD;
            if (a.right != null) {
                q.removeLast();
            }
            if (a.left != null) {
                q.removeLast();
            }
            q.addLast(a);
        }
        return count;
    }

    // nums.len > 0
    private TreeNode buildBinarySearchTree(int[] nums) {
        int n = nums.length;
        TreeNode root = new TreeNode(nums[0]);
        for (int i = 1; i < n; i++) {
            int x = nums[i];
            TreeNode node = root;
            while (true) {
                if (x < node.val) {
                    if (node.left == null) {
                        node.left = new TreeNode(x);
                        break;
                    } else {
                        node = node.left;
                    }
                } else {
                    if (node.right == null) {
                        node.right = new TreeNode(x);
                        break;
                    } else {
                        node = node.right;
                    }
                }
            }
        }
        return root;
    }

    public int numOfWaysTwo(int[] nums) {
        if (nums.length == 0) return 0;
        dp = new int[nums.length + 1][nums.length / 2 + 1];
        TreeNode root = buildBinarySearchTree(nums);

        int[] pair = new int[2];
        count(root, pair);
        int res = pair[0];
        return res == 0 ? MOD - 1 : res - 1;
    }

    private void count(TreeNode root, int[] pair) {
        if (root == null) {
            pair[0] = 1;
            pair[1] = 0;
            return;
        }

        count(root.left, pair);
        long res = pair[0];
        int a = pair[1];
        count(root.right, pair);
        int right = pair[0];
        int b = pair[1];
        res = (res * right) % MOD;
        res = (res * choose(a + b, Math.min(a, b))) % MOD;
        pair[0] = (int) res;
        pair[1] = a + b + 1;
    }


    int[][] dp;
    public int numOfWaysCount(int[] nums) {
        dp = new int[nums.length + 1][nums.length / 2 + 1];
//        System.out.println("input");
//        printRange(nums, 0, nums.length);
//        System.out.println();
        int count = countWays(nums, new int[nums.length], 0, nums.length);
        return count == 0 ? MOD - 1 : count - 1;
    }

    private int countWays(int[] nums, int[]buffer, int start, int end) {
        if (Math.abs(end - start) <= 2) return 1;

        int root = nums[start];
//        System.out.println("pivot is " + root);
        int inc = end > start ? 1 : -1;
        int i = start + inc;
        int j = end - inc;

        for (int k = start + inc; (end - k) * inc > 0; k+=inc) {
            if (nums[k] < root) {
                buffer[i] = nums[k];
                i += inc;
            } else {
                buffer[j] = nums[k];
                j -= inc;
            }
        }


//        System.out.println("partition result from " + (start + inc) + " to " + i + " then to " + end);
//        printRange(buffer, start + inc, i);
//        printRange(buffer, end - inc, j);
//        System.out.println();

        int a = Math.abs(i - start - inc);
        int b = Math.abs(end - inc - j);

        long res = choose(a + b, Math.min(a, b));
        res = (res * countWays(buffer, nums, start + inc, i)) % MOD;
        res = (res * countWays(buffer, nums, end - inc, j)) % MOD;

        return (int) res;
    }

    private void printRange(int[] nums, int start, int end) {
        if (start == end) return;
        int inc = end > start ? 1 : -1;
        for (int i = start; inc > 0 ? i < end : i > end; i+=inc) {
            System.out.print(nums[i]);
            System.out.print(i == end - inc ? "  " : ",");
        }
    }

    private int choose(int n, int k) {
        if (dp[n][k] != 0) return dp[n][k];
        if (k == 0) return 1;
        if (n == k) return 1;
        int res = (choose(n-1,k-1) + choose(n-1, k)) % MOD;
        dp[n][k] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new NumOfWaysBinarySearchTree().numOfWaysCount(new int[]{9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18}));
        System.out.println(new NumOfWaysBinarySearchTree().numOfWaysTwo(new int[]{9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18}));
//        System.out.println(new NumOfWaysBinarySearchTree().numOfWays(new int[]{9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18}));
    }
}
