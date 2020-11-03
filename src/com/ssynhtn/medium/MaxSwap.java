package com.ssynhtn.medium;

import java.util.ArrayDeque;

public class MaxSwap {
    public int maximumSwap(int num) {
        if (num <= 9) return num;
        char[] chs = Integer.toString(num).toCharArray();

        ArrayDeque<Integer> maxIndexes = new ArrayDeque<>();
        maxIndexes.addLast(chs.length - 1);
        for (int i = chs.length - 2; i >= 0; i--) {
            if (chs[i] > chs[maxIndexes.peekLast()]) {
                maxIndexes.addLast(i);
            }
        }

        int i = 0;
        while (i < chs.length) {
            char maxCh = chs[maxIndexes.peekLast()];
            if (maxCh != chs[i]) {
                swap(chs, maxIndexes.peekLast(), i);
                return Integer.parseInt(new String(chs));
            }

            while (i < chs.length && chs[i] == maxCh) {
                i++;
            }

            if (i > maxIndexes.peekLast()) {
                maxIndexes.removeLast();
            }
        }


        return num;
    }

    private void swap(char[] chs, int j, int i) {
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(new MaxSwap().maximumSwap(98368));
    }
}
