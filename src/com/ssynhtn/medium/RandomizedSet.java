package com.ssynhtn.medium;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RandomizedSet {

    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> data = new ArrayList<>();
    /** Initialize your data structure here. */
    public RandomizedSet() {
        
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, data.size());
        data.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Integer index = map.remove(val);
        if (index == null) return false;

        if (index == data.size() - 1) {
            data.remove(index.intValue());
        } else {
            int last = data.remove(data.size() - 1);
            data.set(index, last);
            map.put(last, index);
        }

        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return data.get((int) (Math.random() * data.size()));
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("randomSet.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            String[] commands = sb.toString().split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
