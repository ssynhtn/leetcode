package com.ssynhtn.mock;

import java.util.Arrays;
import java.util.Comparator;

public class ReorderLogs {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String log1, String log2) {
                int index = log1.indexOf(' ');
                String identifier1 = log1.substring(0, index);
                String words1 = log1.substring(index + 1);
                boolean isAlpha1 = words1.charAt(0) > '9';

                index = log2.indexOf(' ');
                String identifier2 = log2.substring(0, index);
                String words2 = log2.substring(index + 1);
                boolean isAlpha2 = words2.charAt(0) > '9';

                if (isAlpha1) {
                    if (isAlpha2) {
                        int comp = words1.compareTo(words2);
                        if (comp != 0) return comp;
                        return identifier1.compareTo(identifier2);
                    } else {
                        return -1;
                    }
                } else {
                    if (isAlpha2) {
                        return 1;
                    } else {
                        return 0;
                    }
                }

            }
        });

        return logs;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ReorderLogs().reorderLogFiles(new String[]{
                "a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo","a2 act car"
        })));
    }
}
