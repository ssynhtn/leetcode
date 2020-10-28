package com.ssynhtn.mock;

import java.util.Arrays;
import java.util.Comparator;

public class ReplaceString {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        Integer[] origIs = new Integer[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            origIs[i] = i;
        }
        Arrays.sort(origIs, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return indexes[o1] - indexes[o2];
            }
        });
        Arrays.sort(indexes);
        int start = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < indexes.length; i++) {
            int index = indexes[i];
            int origI = origIs[i];
            String src = sources[origI];
            if (S.regionMatches(index, src, 0, src.length())) {
                sb.append(S, start, index);
                sb.append(targets[origI]);
                start = index + src.length();
            }
        }
        sb.append(S, start, S.length());
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReplaceString().findReplaceString("vmokgggqzp", new int[]{3,5,1},
                new String[]{"kg","ggq","mo"},
                new String[]{"s","so","bfr"}));
    }
}
