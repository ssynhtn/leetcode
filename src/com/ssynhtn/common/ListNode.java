package com.ssynhtn.common;

import java.util.List;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode random;
    public ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        if (next == null) {
            return "" + val;
        } else {
            return val + "->" + next.toString();
        }
    }

    public static ListNode makeList(List<Integer> data) {
        if (data == null || data.isEmpty()) return null;
        ListNode head = new ListNode(data.get(0));
        ListNode node = head;
        for (int i = 1; i < data.size(); i++) {
            node.next = new ListNode(data.get(i));
            node = node.next;
        }
        return head;
    }
}