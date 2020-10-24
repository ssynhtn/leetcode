package com.ssynhtn.mock;

import com.ssynhtn.common.ListNode;

public class MiddleNode {
    public ListNode middleNode(ListNode head) {
        if (head == null) return null;
        ListNode one = head;
        ListNode two = head.next;

        while (two != null) {
            two = two.next != null ? two.next.next : null;
            one = one.next;
        }


        return one;
    }
}
