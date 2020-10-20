package com.ssynhtn.mock;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BannedWords {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> badWords = new HashSet<>();
        for (String b : banned) {
            badWords.add(b);
        }

        Map<String, Integer> counts = new HashMap<>();

        int i = 0;
        int n = paragraph.length();
        int j;
        while (i < n) {
            while (i < n && !Character.isAlphabetic(paragraph.charAt(i))) {
                i++;
            }

            if (i < n) {
                j = i + 1;
                while (j < n && Character.isAlphabetic(paragraph.charAt(j))) {
                    j++;
                }

                String word = paragraph.substring(i, j).toLowerCase();
                if (!badWords.contains(word)) {
                    Integer lastCount = counts.get(word);
                    counts.put(word, lastCount == null ? 1 : lastCount + 1);
                }

                i = j;
            }
        }

        int max = 0;
        String word = null;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                word = entry.getKey();
            }
        }
        return word;
    }

    public static void main(String[] args) {
        System.out.println(new BannedWords().mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}));
    }
}
