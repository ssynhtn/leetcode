package com.ssynhtn.contest;

import com.ssynhtn.common.ListNode;
import com.sun.org.apache.xerces.internal.impl.XML11NSDocumentScannerImpl;

/**
 * 1 2 * (3) 4 5
 *
 * 1 2 3 * (4) 5 6
 *
 * mid = n/2
 */
class FrontMiddleBackQueue {

    static class ListNode {
        public int val;
        public ListNode next;
        public ListNode prev;

        public ListNode(int val) {
            this.val = val;
        }
    }

    ListNode head;
    ListNode tail;
    ListNode mid;
    int size;
    public FrontMiddleBackQueue() {

    }
    
    public void pushFront(int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            head = tail = mid = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;

            if (size % 2 == 0) {
                mid = mid.prev;
            }

        }

        size++;
    }
    
    public void pushMiddle(int val) {
        ListNode node = new ListNode(val);
        if (mid == null) {
            head = tail = mid = node;
        } else {
            if (mid.prev == null) {
                mid.prev = node;
                node.next = mid;
                head = node;
            } else {
                mid.prev.next = node;
                node.prev = mid.prev;
                node.next = mid;
                mid.prev = node;

                if (size % 2 == 0) {
                    mid = node;
                }
            }
        }

        size++;
    }
    
    public void pushBack(int val) {
        ListNode node = new ListNode(val);
        if (tail == null) {
            head = tail = mid = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;

            if (size % 2 == 1) {
                mid = mid.next;
            }

        }

        size++;
    }
    
    public int popFront() {
        if (head == null) return -1;
        int res = head.val;
        if (head == tail) {
            head = tail = mid = null;
        } else {
            if (size % 2 == 1) {
                mid = mid.next;
            }

            ListNode next = head.next;
            next.prev = null;
            head.next = null;
            head = next;


        }

        size--;
        return res;
    }
    
    public int popMiddle() {
        if (mid == null) return -1;

        ListNode remove = size % 2 == 1 ? mid : mid.prev;
        int res = remove.val;

        if (head == tail) {
            head = tail = mid = null;
        } else {
            if (size % 2 == 1) {
                mid = mid.next;
            }
            if (remove == head) {
                head = head.next;
                remove.next.prev = null;
                remove.next = null;
            } else {
                ListNode prev = remove.prev;
                ListNode next = remove.next;
                prev.next = next;
                next.prev = prev;
            }
        }

        size--;
        return res;
    }
    
    public int popBack() {
        if (head == null) return -1;
        int res = tail.val;

        if (head == tail) {
            head = tail = mid = null;
        } else {
            if (size % 2 == 0) {
                mid = mid.prev;
            }

            ListNode prev = tail.prev;
            prev.next = null;
            tail.prev = null;
            tail = prev;

        }

        size--;
        return res;
    }
}