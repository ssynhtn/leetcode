package com.ssynhtn.contest;

public class LongestKeyStroke {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int duration = releaseTimes[0];
        char ch = keysPressed.charAt(0);

        int n = releaseTimes.length;
        for (int i = 1; i < n; i++) {
            int du = releaseTimes[i] - releaseTimes[i - 1];
            if (du > duration) {
                duration = du;
                ch = keysPressed.charAt(i);
            } else if (du == duration) {
                if (keysPressed.charAt(i) > ch) {
                    ch = keysPressed.charAt(i);
                }
            }
        }

        return ch;
    }

    public static void main(String[] args) {
        System.out.println(new LongestKeyStroke().slowestKey(new int[]{9,29,49,50}, "cbcd"));
    }
}
