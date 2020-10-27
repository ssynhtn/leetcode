package com.ssynhtn.card.list;

import com.ssynhtn.common.ListNode;

public class IsPalindrome {
    ListNode temp;
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }

        return isPalindrome(head, count);

    }

    // if true, point temp to the last of the palindrome
    private boolean isPalindrome(ListNode head, int n) {
        if (n == 0) {
            temp = null;
            return true;
        }

        if (n == 1) {
            temp = head;
            return true;
        }

        if (n == 2) {
            boolean res = head.val == head.next.val;
            if (res) temp = head.next;
            return res;
        }

        boolean isPalindrome = isPalindrome(head.next, n - 2);
        if (isPalindrome) {
            boolean res = head.val == temp.next.val;
            if (res) {
                temp = temp.next;
            }
            return res;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new IsPalindrome().isPalindrome(ListNode.makeList(1, 2, 2,1)));
    }
}
