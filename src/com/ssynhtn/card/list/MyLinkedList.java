package com.ssynhtn.card.list;

class MyLinkedList {

    static class MyNode {
        int val;
        MyNode next;

        public MyNode(int val) {
            this.val = val;
        }
    }

    MyNode head;
    MyNode tail;
    int size;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        MyNode node = head;
        while (index > 0) {
            index--;
            node = node.next;
        }
        return node.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        MyNode node = new MyNode(val);
        node.next = head;
        head = node;
        if (tail == null) {
            tail = node;
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

        MyNode node = head;
        index--;
        while (index > 0) {
            node = node.next;
            index--;
        }

        MyNode x = new MyNode(val);
        x.next = node.next;
        node.next = x;

        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        if (index == 0) {
            head = head.next;
            if (tail != null && tail.next == head) {
                tail = null;
            }
            size--;
            return;
        }

        index = index - 1;
        MyNode node = head;
        while (index > 0) {
            node = node.next;
            index--;
        }

        if (node.next == tail) {
            node.next = null;
            tail = node;
        } else {
            node.next = node.next.next;
        }
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