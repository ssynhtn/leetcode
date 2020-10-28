package com.ssynhtn.medium;



public class FlatternList {
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };

    public Node flattenRec(Node head) {
        if (head == null) return null;
        Node[] ht = new Node[2];
        flattenHeadTail(head, ht);
        return head;
    }

    // head != null
    private void flattenHeadTail(Node head, Node[] ht) {
        Node tail = head;

        Node next = tail.next;
        if (head.child != null) {
            flattenHeadTail(head.child, ht);
            tail.next = ht[0];
            ht[0].prev = head;
            head.child = null;
            tail = ht[1];
        }

        if (next != null) {
            flattenHeadTail(next, ht);
            tail.next = ht[0];
            ht[0].prev = tail;
            tail = ht[1];
        }

        ht[0] = head;
        ht[1] = tail;
    }
}
