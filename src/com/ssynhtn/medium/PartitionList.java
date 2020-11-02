package com.ssynhtn.medium;

import com.ssynhtn.common.ListNode;

public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode left = null;
        ListNode leftTail = null;
        ListNode right = null;
        ListNode rightTail = null;

        while (head != null) {
            if (head.val < x) {
                if (left == null) {
                    left = leftTail = head;
                } else {
                    leftTail.next = head;
                    leftTail = head;
                }
            } else {
                if (right == null) {
                    right = rightTail = head;
                } else {
                    rightTail.next = head;
                    rightTail = head;
                }
            }
            head = head.next;
        }

        if (leftTail != null) {
            leftTail.next = right;
        }
        if (rightTail != null) {
            rightTail.next = null;
        }
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        System.out.println(new PartitionList().partition(ListNode.makeList(1,4,3,2,5,2), 3));
    }
}
