package com.ssynhtn.easy;

import com.ssynhtn.common.ListNode;

import java.util.ArrayDeque;
import java.util.Arrays;

public class ReverseLinkedList {
    public ListNode reverseListRec(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode next = head.next;
        ListNode reverseNext = reverseListRec(next);
        next.next = head;
        head.next = null;

        return reverseNext;
    }

    public ListNode reverseListStack(ListNode head) {
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        if (stack.isEmpty()) return null;
        ListNode res = stack.poll();
        ListNode node = res;
        while (!stack.isEmpty()) {
            node.next = stack.poll();
            node = node.next;
        }
        node.next = null;
        return res;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;

    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) return head;
        if (m == n) return head;

        int len = n - m + 1;

        m--;
        ListNode prev = null;
        ListNode curr = head;

        while (m > 0) {
            prev = curr;
            curr = curr.next;
            m--;
        }

        ListNode tail = curr;
        ListNode reversed = null;
        while (len > 0) {
            ListNode next = curr.next;
            curr.next = reversed;
            reversed = curr;
            curr = next;
            len--;
        }

        tail.next = curr;
        if (prev == null) {
            return reversed;
        } else {
            prev.next = reversed;
            return head;
        }

    }

    public ListNode reverseBetweenRec(ListNode head, int m, int n) {
        if (m == n) return head;
        if (m == 1) {
            return reverseHead(head, n - m + 1);
        }
        head.next = reverseBetween(head.next, m -1, n-1);
        return head;
    }

    private ListNode reverseHead(ListNode head, int n) {
        if (n <= 1) return head;
        ListNode next = head.next;
        ListNode newHead = reverseHead(next, n-1);
        head.next = next.next;
        next.next = head;
        return newHead;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseLinkedList().reverseBetweenRec(ListNode.makeList(Arrays.asList(1, 2, 3, 4, 5)), 2, 4));
    }
}
