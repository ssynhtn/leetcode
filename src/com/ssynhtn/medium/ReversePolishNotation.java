package com.ssynhtn.medium;

import java.util.ArrayDeque;

public class ReversePolishNotation {
    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> nums = new ArrayDeque<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                int b = nums.removeLast();
                int a = nums.removeLast();
                nums.addLast(a + b);
            } else if (token.equals("-")) {
                int b = nums.removeLast();
                int a = nums.removeLast();
                nums.addLast(a - b);
            } else if (token.equals("*")) {
                int b = nums.removeLast();
                int a = nums.removeLast();
                nums.addLast(a * b);
            } else if (token.equals("/")) {
                int b = nums.removeLast();
                int a = nums.removeLast();
                nums.addLast(a / b);
            } else {
                nums.addLast(Integer.parseInt(token));
            }
        }

        return nums.removeLast();
    }

    public static void main(String[] args) {
        System.out.println(new ReversePolishNotation().evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }
}
