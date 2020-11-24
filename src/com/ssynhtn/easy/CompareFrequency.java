package com.ssynhtn.easy;

import java.util.Arrays;

class CompareFrequency {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int m = words.length;
        int[] counts = new int[m];
        for (int i = 0; i < m; i++) {
            counts[i] = freq(words[i]);
        }
        
        Arrays.sort(counts);
        
        int n = queries.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int count = freq(queries[i]);
            
            int index = Arrays.binarySearch(counts, count);
            
            int more;
            if (index < 0) {
                index = -1 - index;
                more = m - index;
            } else {
                while (index < m && counts[index] == count) {
                    index++;
                }
                more = m - index;
            }
            
            res[i] = more;
        }
        
        return res;
    }
    
    int freq(String s) {
        int count = 0;
        char minCh = Character.MAX_VALUE;
        
        for (char ch : s.toCharArray()) {
            if (ch < minCh) {
                minCh = ch;
                count = 1;
            } else if (ch == minCh) {
                count++;
            }
        }
        
        return count;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CompareFrequency().numSmallerByFrequency(new String[]{"cbd"}, new String[]{"zaaaz"})));
    }
}