package com.ssynhtn.hard;

import java.util.*;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        int len = s.length();
        int[] typeCounts = new int[len];
        int[] lastIndex = new int[26];

        int totalType = 0;
        for (int i = len - 1; i >= 0; i--) {
            int ch = s.charAt(i) - 'a';
            if (lastIndex[ch] == 0) {
                typeCounts[i] = ++totalType;
                lastIndex[ch] = i;
            } else {
                typeCounts[i] = totalType;
            }

        }

        char[] buffer = new char[totalType];
        int bIndex = 0;
        int[] flags = new int[26];
        List<Integer> sortedList = new ArrayList<>();
        int sortedIndex;


        int i = 0;
        int type = totalType;
        int minIndex;
        while (type > 0) {
             while (flags[s.charAt(i) - 'a'] == 1) {
                 i++;
             }

            char min = s.charAt(i);
             minIndex = i;
             sortedIndex = -1 - Collections.binarySearch(sortedList, i, Collections.reverseOrder());
             i++;
             while (i < len) {
                 char ch = s.charAt(i);
                 if (flags[ch - 'a'] == 1) {
                     if (sortedIndex > 0 && sortedList.get(sortedIndex - 1) == i) {
                         sortedIndex--;
                     }
                     i++;
                     continue;
                 }

                 if (typeCounts[i] - sortedIndex < type) {
                     break;
                 }

                 if (ch < min) {
                     min = ch;
                     minIndex = i;
                 }
                 i++;
             }

             flags[min - 'a'] = 1;
             buffer[bIndex++] = min;
             i = minIndex + 1;
             type--;
             int lstIndex = lastIndex[min - 'a'];

             sortedList.add(lstIndex);
             sortedList.sort(Collections.reverseOrder());
        }
//
//        int i = 0;
//        int type = totalType = typeCounts[0] // total
//        lastIndex
//
//        LikedHashSet<Char> list
//
//        char min = Char.max
//
//        minIndexes = sortedList in reverse order
//
//        while list.size < totalType
//        while i in bounds && ch at i is already chosen:
//        i++
//        min = charAt i++
//        sortedIndex = find(sortedList, i)
//
//        while (i in bounds)
//        if char at i already chosen:
//        if (sortedList[sortedIndex] == i) sortedIndex++
//        skip
//        if (type[i] - sortedIndex) < type) break
//        if (char at i < min) {
//            update min
//            markIndex as i backup
//        }
//
//        put min into list, get last index of min, add it to sortedList
//        set i to i backup + 1
//
//
//        return list.toString


        return new String(buffer);
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("bcabc"));
    }
}
