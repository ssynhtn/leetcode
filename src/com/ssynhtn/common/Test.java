package com.ssynhtn.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
//        for (int i = 1; i <= 20; i++) {
//            input.add(i);
//        }
        for (int i = 0; i < 10; i++) {
            input.add((int) (Math.random() * 5 + 1));
        }
//        Collections.shuffle(input);
        Collections.sort(input);
        System.out.println(input);
    }


}
