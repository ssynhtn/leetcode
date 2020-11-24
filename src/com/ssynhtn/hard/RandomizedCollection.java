package com.ssynhtn.hard;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RandomizedCollection {

    static class Node {
        int value;
        int index;
        Node next;
    }

    List<Node> data;
    Map<Integer, Node> map;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        data = new ArrayList<>();
        map = new HashMap<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        Node node = new Node();
        node.value = val;
        node.index = data.size();
        data.add(node);

        Node last = map.remove(val);
        node.next = last;
        map.put(val, node);

        return last == null;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Node node = map.get(val);
        if (node == null) return false;

        map.put(val, node.next);

        if (node.index == data.size() - 1) {
            data.remove(node.index);
        } else {
            Node last = data.remove(data.size() - 1);
            data.set(node.index, last);
            last.index = node.index;
        }

        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return data.get((int)(Math.random() * data.size())).value;
    }

    public static void main(String[] args) {
        RandomizedCollection collection = new RandomizedCollection();
        collection.insert(0);
        collection.insert(1);
        collection.remove(0);
        collection.insert(2);
        collection.remove(1);
        System.out.println(collection.getRandom());
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */