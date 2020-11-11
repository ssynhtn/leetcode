package com.ssynhtn.challenge;

import com.ssynhtn.common.ListNode;

public class AddTwoNums {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode a = l1;
        ListNode b = l2;
        while (a != null && b != null) {
            a = a.next;
            b = b.next;
        }

        ListNode head = new ListNode(0);
        ListNode not9 = head;
        ListNode tail = head;
        ListNode one, two, extra;
        if (b == null) {
            one = l1;
            two = l2;
            extra = a;
        } else {
            one = l2;
            two = l1;
            extra = b;
        }

        while (extra != null) {
            tail.next = new ListNode(one.val);
            extra = extra.next;
            one = one.next;
            tail = tail.next;
            if (tail.val < 9) {
                not9 = tail;
            }
        }

//        System.out.println(head);
//        System.out.println(one);
//        System.out.println(two);
//        System.out.println(not9);

        while (one != null) {
            int sum = one.val + two.val;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            if (sum >= 10) {
                not9.val++;
                not9 = not9.next;
                while (not9 != null && not9.val == 9) {
                    not9.val = 0;
                    not9 = not9.next;
                }

                not9 = tail;
            } else {
                if (tail.val < 9) {
                    not9 = tail;
                }
            }

//            System.out.println("head " + head);
//            System.out.println("tail" +  tail);
            one = one.next;
            two = two.next;

        }

        if (head.val != 0) return head;
        return head.next;
    }

    public static void main(String[] args) {
        System.out.println(new AddTwoNums().addTwoNumbers(ListNode.makeList(6,4,5,0,4,4,9,4,1), ListNode.makeList(3,8,8,0,3,0,1,4,8)));
    }
}
