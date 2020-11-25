package com.ssynhtn.hard;

import java.util.ArrayDeque;

class BasicCalcStack {


//0: we at at the start of a sub expression(including this)
//1: we have some x, and we are collecting others
    int i = 0;
    char[] chs;
    int n;

    int state;

    void skipSpaces() {
        while (i < n && chs[i] == ' ') {
            i++;
        }
    }

    public int calculate(String s) {
//        System.out.println("input " + s);
        // int, left (, +/-
        ArrayDeque<Object> q = new ArrayDeque<>();
        chs = s.toCharArray();
        n = chs.length;

        char ch;
        int x = 0;
        while (i < n) {
            skipSpaces();
            if (state == 0) {
                if (i == n) break;

                ch = chs[i++];
                if (ch == '(') {
//                    System.out.println("push (");
                    q.addLast(ch);
                    continue;
                }

                if (ch == '+' || ch == '-') {
                    throw new RuntimeException("unexpected " + ch + " at " + (i - 1));
                }

                x = ch - '0';
                while (i < n && (ch = chs[i]) >= '0' && ch <= '9') {
                    x = x * 10 + ch - '0';
                    i++;
                }

                state = 1;
//                System.out.println("acc x " + x + ", switch to collect mode");
                continue;
            }

            if (i < n) {
                ch = chs[i++];
                if (ch == ')') {
                    Object open = q.removeLast(); // pop '('
                    Object last = q.peekLast();
                    if (last != null && (last.equals('+') || last.equals('-'))) {
                        q.removeLast();
                        int prevNum = (int) q.removeLast();
                        int tempX = x;
                        x = last.equals('+') ? prevNum + x : prevNum - x;
//                        System.out.println(prevNum + " " + last + " " + tempX + " -> " + x);
                    } else {
//                        System.out.println("popping " + open);
                    }
                    continue;
                }

                char op = ch;
                if (op != '+' && op != '-') throw new RuntimeException("unexpected ch " + op + " at " + (i-1));
                skipSpaces();
                ch = chs[i++];
                if (ch == '(') {
                    q.addLast(x);
                    q.addLast(op);
                    q.addLast(ch);
//                    System.out.println("push " + x + ", " + op + ", " + ch + ", switch to open mode");
                    state = 0;
                    continue;
                }



                int y = ch - '0';
                while (i < n && (ch = chs[i]) >= '0' && ch <= '9') {
                    y = y * 10 + ch - '0';
                    i++;
                }

                x = op == '+' ? x + y : x - y;
//                System.out.println("acc  y " + y + ", x -> " + x);

            }

        }


        return x;

    }


    public static void main(String[] args) {
        System.out.println(new BasicCalcStack().calculate("1 + 1"));
        System.out.println(new BasicCalcStack().calculate(" 2-1 + 2 "));
        System.out.println(new BasicCalcStack().calculate("(1+(4+5+2)-3)+(6+8)"));
    }

}