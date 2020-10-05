package com.ssynhtn.mock;

public class BullsCows {
    public String getHint(String secret, String guess) {
        int[] counts = new int[10];
        int[] guessCounts = new int[10];
        int match = 0;

        for (int i = 0; i < secret.length(); i++) {
            int d = secret.charAt(i) - '0';
            int gd = guess.charAt(i) - '0';

            if (d == gd) {
                match++;
            } else {
                counts[d]++;
                guessCounts[gd]++;
            }
        }

        int cows = 0;
        for (int i = 0; i <= 9; i++) {
            cows += Math.min(counts[i], guessCounts[i]);
        }
        return match + "A" + cows + "B";
    }
}
