package com.ssynhtn.card.list;

import javax.swing.event.MouseInputListener;

class MyDoubleLinkedList {

    static class MyNode {
        int val;
        MyNode next;
        MyNode prev;

        public MyNode(int val) {
            this.val = val;
        }
    }

    MyNode head;
    MyNode tail;
    int size;

    /** Initialize your data structure here. */
    public MyDoubleLinkedList() {
        
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;

        if (index < size / 2) {
            MyNode node = head;
            while (index > 0) {
                index--;
                node = node.next;
            }
            return node.val;
        } else {
            index = size - 1 - index;
            MyNode node = tail;
            while (index > 0) {
                index--;
                node = node.prev;
            }
            return node.val;

        }

    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        MyNode node = new MyNode(val);

        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }

        size++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        MyNode node = new MyNode(val);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }
    
    /** Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }

        MyNode node;
        if (index < size / 2) {
            node = head;
            index--;
            while (index > 0) {
                node = node.next;
                index--;
            }


        } else {
            node = tail;
            index = size - index;
            while (index > 0) {
                node = node.prev;
                index--;
            }
        }

        MyNode x = new MyNode(val);
        x.next = node.next;
        x.next.prev = x;
        node.next = x;
        x.prev = node;

        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        if (size == 1) {
            head = tail = null;
            size = 0;
            return;
        }

        if (index == 0) {
            head = head.next;
            head.prev = null;
            size--;
            return;
        }

        if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
            size--;
            return;
        }


        MyNode node;
        if (index < size / 2) {
            index = index - 1;
            node = head;
            while (index > 0) {
                node = node.next;
                index--;
            }
        } else {
            node = tail;
            index = size - index;
            while (index > 0){
                node = node.prev;
                index--;
            }
        }

        node.next = node.next.next;
        node.next.prev = node;
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */