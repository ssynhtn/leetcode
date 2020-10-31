package com.ssynhtn.medium;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache2 {
    private LinkedHashMap<Integer, Integer> map;

    public LRUCache2(int capacity) {
        map = new LinkedHashMap<Integer, Integer>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        Integer res = map.get(key);
        return res != null ? res : -1;
    }

    public void put(int key, int value) {
        map.put(key, value);
    }
}
