package com.ssynhtn.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MagicDictionary {

    /** Initialize your data structure here. */
    Map<String, Integer> patternCount;
    Set<String> words;
    Set<Integer> lens;
    public MagicDictionary() {
        
    }
    
    public void buildDict(String[] dictionary) {
        patternCount = new HashMap<>();
        words = new HashSet<>();
        lens = new HashSet<>();

        for (String w : dictionary) {
            words.add(w);
            lens.add(w.length());
            char[] chs = w.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char temp = chs[i];
                chs[i] = '*';
                patternCount.merge(new String(chs), 1, Integer::sum);
                chs[i] = temp;
            }
        }
    }
    
    public boolean search(String searchWord) {
        if (!lens.contains(searchWord.length())) return false;
        for (int i = 0; i < searchWord.length(); i++) {
            String key = searchWord.substring(0, i) + "*" + searchWord.substring(i + 1);

            int count = patternCount.getOrDefault(key, 0);
            if (count > 1) return true;

            if (count == 1) {
                if (!words.contains(searchWord)) {
                    return true;
                }
            }

        }

        return false;
    }
}
