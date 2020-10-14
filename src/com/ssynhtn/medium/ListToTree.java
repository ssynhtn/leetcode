package com.ssynhtn.medium;

import com.ssynhtn.common.ListNode;
import com.ssynhtn.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class ListToTree {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        List<ListNode> nodes = new ArrayList<>();
        while (head != null) {
            nodes.add(head);
            head = head.next;
        }

        return convert(nodes, 0, nodes.size() - 1);
    }

    private TreeNode convert(List<ListNode> nodes, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nodes.get(mid).val);
        root.left = convert(nodes, start, mid - 1);
        root.right = convert(nodes, mid + 1, end);
        return root;
    }
}
