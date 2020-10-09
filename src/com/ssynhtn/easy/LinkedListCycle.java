package com.ssynhtn.easy;

import com.ssynhtn.common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
    public boolean hasCycleSet(ListNode head) {
        Set<ListNode> seen = new HashSet<>();

        while (head != null) {
            if (!seen.add(head)) return true;
            head = head.next;
        }

        return false;

    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (true) {
            if (fast == null || fast.next == null) return false;
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) return true;
        }
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                while (slow != head) {
                    slow = slow.next;
                    head = head.next;
                }

                return slow;
            }
        }
    }
}
