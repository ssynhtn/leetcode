package com.ssynhtn.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            input.add(i);
        }
        Collections.shuffle(input);
        System.out.println(input);
    }
}