package com.ssynhtn.contest;

class MaxRepeating {
    public int maxRepeating(String sequence, String word) {
        int n = sequence.length();
        int m = word.length();
        int limit = n / m;
        int max = 0;
        String w = "";
        for (int i = 1; i <= limit; i++) {
            w = w + word;
            if (sequence.contains(w)) {
                max = i;
            } else {
                break;
            }
        }

        return max;


    }

    public static void main(String[] args) {
        System.out.println(new MaxRepeating().maxRepeating("ababc", "ab"));
        System.out.println(new MaxRepeating().maxRepeating("ababc", "ba"));

    }
}