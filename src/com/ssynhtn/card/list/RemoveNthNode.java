package com.ssynhtn.card.list;

import com.ssynhtn.common.ListNode;
import sun.jvm.hotspot.utilities.HeapHprofBinWriter;

import java.util.Arrays;

class RemoveNthNode {
    // 1 <= n <= listSize
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;

        ListNode next = head;
        while (n > 0 && next != null) {
            next = next.next;
            n--;
        }

        if (n > 0 || next == null) {
            // len <= n
            return head.next;
        }

        ListNode pre = head;
        while (next.next != null) {
            pre = pre.next;
            next = next.next;
        }

        if (pre.next != null) {
            pre.next = pre.next.next;
        }

        return head;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveNthNode().removeNthFromEnd(ListNode.makeList(1), 1));
    }
}