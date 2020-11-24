package com.ssynhtn.hard;

import java.util.HashMap;
import java.util.Map;

class LFUCache2D {

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;
        int useCount;


        public String toStringRev() {
            if (prev == null) {
                return key + ":" + value;
            } else {
                return prev.toStringRev() + "<-" + key + ":" + value;
            }
        }
        @Override
        public String toString() {
            if (next == null) {
                return key + ":" + value;
            } else {
                return key + ":" + value + "->" + next.toString();
            }
        }
    }

    Map<Integer, Node> map;
    Map<Integer, Node> useCountMap;
    Node head;
    Node tail;

    final int capacity;

    public LFUCache2D(int capacity) {
        map = new HashMap<>();
        useCountMap = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        touchNode(node);
        
        return node.value;
    }
    
    // node != null
    void touchNode(Node node) {
        Node next = node.next;
        node.useCount++;
        Node toJump = useCountMap.get(node.useCount);
        if (toJump == null) {
            toJump = useCountMap.get(node.useCount - 1);
        }
        if (toJump != null && toJump != node) {
            jumpBefore(node, toJump);
        }
        if (useCountMap.get(node.useCount - 1) == node) {
            if (next != null && next.useCount == node.useCount - 1) {
                useCountMap.put(next.useCount, next);
            } else {
                useCountMap.remove(node.useCount - 1);
            }
        }

        useCountMap.put(node.useCount, node);
    }

    // node != null, twoHead != null
    private void jumpBefore(Node node, Node toJump) {
        node.prev.next = node.next;
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        if (toJump.prev != null) {
            toJump.prev.next = node;
        } else {
            head = node;
        }

        node.prev = toJump.prev;
        node.next = toJump;
        toJump.prev = node;

    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (map.size() == capacity && !map.containsKey(key)) {
            removeTail();
        }
        
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            touchNode(node);
            return;
        }
        
        node = new Node();
        node.key = key;
        node.value = value;
        map.put(key, node);
        if (tail == null) {
            head = tail = node;
            node.useCount++;
            useCountMap.put(1, node);
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
            touchNode(node);
        }
    }
    
    void removeTail() {
        if (tail == null) return;
        map.remove(tail.key);
        if (useCountMap.get(tail.useCount) == tail) {
            useCountMap.remove(tail.useCount);
        }
        if (head == tail) {
            head = tail = null;
        } else {
            Node prev = tail.prev;
            prev.next = null;
            tail = prev;
        }
    }

    /**
     * ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
     * [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
     */
    public static void main(String[] args) {
        LFUCache2D cache = new LFUCache2D(2);
        cache.put(1, 1);
        System.out.println(cache.head);
        System.out.println(cache.tail.toStringRev());
        cache.put(2, 2);
        System.out.println(cache.head);
        System.out.println(cache.tail.toStringRev());
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.head);
        System.out.println(cache.tail.toStringRev());
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(4, 4);
        System.out.println(cache.head);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));

    }
}


/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */