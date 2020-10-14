package com.ssynhtn.card.list;

import com.ssynhtn.common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class IntesectionOfTwoList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> aNodes = new HashSet<>();
        Set<ListNode> bNodes = new HashSet<>();

        while (true) {
            if (headA != null) {
                if (bNodes.contains(headA)) return headA;
                aNodes.add(headA);
                headA = headA.next;
            }

            if (headB != null) {
                if (aNodes.contains(headB)) return headB;
                bNodes.add(headB);
                headB = headB.next;
            }

            if (headA == null && headB == null) break;
        }
        return null;
    }
    public ListNode getIntersectionNodeTwoPointer(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode node = headA;
        while (node.next != null) {
            node = node.next;
        }
        node.next = headA;


        ListNode fast = headB;
        ListNode slow = headB;

        while (true) {
            if (fast == null || fast.next == null) {
                node.next = null;
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                while (slow != headB) {
                    slow = slow.next;
                    headB = headB.next;
                }

                node.next = null;
                return slow;
            }
        }
    }
}
