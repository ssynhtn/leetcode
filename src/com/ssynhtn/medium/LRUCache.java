package com.ssynhtn.medium;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    static class Item {
        int key;
        int value;
        Item next;
        Item prev;
    }

    Item head;
    Item tail;
    Map<Integer, Item> map;
    private final int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = Math.max(1, capacity);
    }

    public int get(int key) {
        Item item = map.get(key);
        moveToHead(item);

        return item != null ? item.value : -1;
    }

    private void moveToHead(Item item) {
        if (item != null && item != head) {
            item.prev.next = item.next;
            if (item.next != null) {
                item.next.prev = item.prev;
            }

            if (item == tail) {
                tail = item.prev;
            }

            item.prev = null;
            item.next = head;
            head.prev = item;
            head = item;
        }
    }


    public void put(int key, int value) {
        Item item = map.get(key);
        if (item != null) {
            moveToHead(item);
            item.value = value;
            return;
        }

        item = new Item();
        item.key = key;
        item.value = value;
        map.put(key, item);


        if (head == null) {
            head = tail = item;
        } else {
            item.next = head;
            head.prev = item;
            head = item;
        }

        if (map.size() > capacity) {
            map.remove(tail.key);
            tail = tail.prev;
            tail.next.prev = null;
            tail.next = null;
        }
    }
}
