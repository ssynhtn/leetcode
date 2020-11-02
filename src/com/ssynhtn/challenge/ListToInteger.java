package com.ssynhtn.challenge;

import com.ssynhtn.common.ListNode;

public class ListToInteger {
    public int getDecimalValue(ListNode head) {
        int x = 0;
        while (head != null) {
            x = (x << 1) | head.val;
            head = head.next;
        }

        return x;
    }

    public static void main(String[] args) {
        System.out.println(new ListToInteger().getDecimalValue(ListNode.makeList(1, 0, 1)));
    }
}
