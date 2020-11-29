package com.ssynhtn.contest;

import com.ssynhtn.common.ListNode;

class MergeList {
    // a >= 1, a <= b,  b < n-1
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode prev;
        prev = list1;
        for (int i = 0; i < a-1; i++) {
            prev = prev.next;
        }

        ListNode next = prev;
        for (int i = 0; i < b-a+2; i++) {
            next = next.next;
        }

        prev.next = list2;
        ListNode last = list2;
        while (last.next != null) {
            last = last.next;
        }

        last.next = next;
        return list1;
    }
}