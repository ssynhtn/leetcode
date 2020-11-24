package com.ssynhtn.hard;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class LFUCacheHeap {

    static class Node {
        int key;
        int value;
        int useCount;
        int touchTime;
    }

    Map<Integer, Node> map;
    PriorityQueue<Node> heap;
    final int capacity;
    int time;

    public LFUCacheHeap(int capacity) {
        map = new HashMap<>();
        heap = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node one, Node two) {
                if (one.useCount < two.useCount) return -1;
                if (one.useCount > two.useCount) return 1;
                return one.touchTime - two.touchTime;
            }
        });
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        heap.remove(node);
        node.useCount++;
        node.touchTime= ++time;
        heap.add(node);
        
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (map.size() == capacity && !map.containsKey(key)) {
            Node node = heap.remove();
            map.remove(node.key);
        }
        
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            heap.remove(node);
            node.useCount++;
            node.touchTime = ++time;
            heap.add(node);
            return;
        }
        
        node = new Node();
        node.key = key;
        node.value = value;
        map.put(key, node);
        node.useCount++;
        node.touchTime = ++time;
        heap.add(node);
    }

    /**
     * ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
     * [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
     */
    public static void main(String[] args) {
        LFUCacheHeap cache = new LFUCacheHeap(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(4, 4);
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