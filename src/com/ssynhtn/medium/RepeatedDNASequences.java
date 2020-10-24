package com.ssynhtn.medium;

import java.util.*;

public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> repeats = new HashSet<>();
        Set<String> seen = new HashSet<>();

        int n = s.length();
        int end = n - 10;
        for (int i = 0; i <= end; i++) {
            String token = s.substring(i, i + 10);
            if (!seen.add(token)) {
                repeats.add(token);
            }
        }

        return new ArrayList<>(repeats);

    }


    public List<String> findRepeatedDnaSequencesMap(String s) {
        Map<String, Integer> repeats = new HashMap<>();

        int n = s.length();
        int end = n - 10;
        for (int i = 0; i <= end; i++) {
            String token = s.substring(i, i + 10);
            repeats.merge(token, 1, Integer::sum);

        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : repeats.entrySet()) {
            if (entry.getValue() >= 2) {
                result.add(entry.getKey());
            }

        }

        return result;

    }

    public static void main(String[] args) {
        System.out.println(new RepeatedDNASequences().findRepeatedDnaSequencesMap("AAAAAAAAAAA"));
    }
}
