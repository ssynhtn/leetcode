package com.ssynhtn.daily;

import com.ssynhtn.common.ListNode;

import java.util.Arrays;

public class SortLinkedList {
    public ListNode sortList(ListNode head) {
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }

        return sortList(head, len);
    }

    private ListNode sortList(ListNode head, int n) {
        if (n <= 1) return head;

        int m = n / 2;
        ListNode node = head;
        int i = 0;
        while (i < m - 1) {
            node = node.next;
            i++;
        }

        ListNode second = node.next;
        node.next = null;

        System.out.println("split to " + head + ", and " + second);
        ListNode one = sortList(head, m);
        ListNode two = sortList(second, n - m);
        System.out.println("after sorting subs, now " + one + ", and " + two);

        ListNode res;
        ListNode tail;

        if (one.val < two.val) {
            res = tail = one;
            one = one.next;
        } else {
            res = tail = two;
            two = two.next;
        }

        while (one != null && two != null) {
            if (one.val < two.val) {
                tail = tail.next = one;
                one = one.next;
            } else {
                tail = tail.next = two;
                two = two.next;
            }
        }

        if (one == null) {
            tail.next = two;
        } else {
            tail.next = one;
        }

        System.out.println("merged to " + res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SortLinkedList().sortList(ListNode.makeList(Arrays.asList(4, 2, 1, 3))));
    }
}
