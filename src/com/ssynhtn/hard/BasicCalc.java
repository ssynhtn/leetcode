package com.ssynhtn.hard;

class BasicCalc {
//    x = next value
//while (has next)
//    op = next op
//    y = next value
//    x = x op y
//
//    next value
//    skip white space
//    if op: bad
//    if digit: collect and return
//            if '(', next value, skip ')', return
//
//    next op:
//    skip spaces
//    if + or -, return, else bad

    char[] chs;
    int n;
    int i = 0;
    public int calculate(String s) {
        chs = s.toCharArray();
        n = chs.length;

        return nextExpression();

    }

    private int nextExpression() {
        int x = nextValue();
//        System.out.println("first x " + x);
        while (i < n) {
            skipSpaces();
            if (i == n || chs[i] == ')') break;

            char op = nextOp();
//            System.out.println("op " + op);
            int y = nextValue();
//            System.out.println("y " + y);

            x = op == '+' ? x + y : x - y;
//            System.out.println("after op " + x);
        }

        return x;
    }

    private char nextOp() {
        skipSpaces();

        if (chs[i] != '+' && chs[i] != '-') throw new IllegalStateException("next op is " + chs[i]);
        return chs[i++];
    }

    int nextValue() {
        skipSpaces();

        char ch = chs[i];
        if (ch == '+' || ch ==  '-') {
            throw new IllegalStateException("fisrt char of next value is " + ch);
        }

        if (ch >= '0' && ch <= '9') {
            int sum = ch - '0';
            i++;
            while (i < n && chs[i] >= '0' && chs[i] <= '9') {
                sum = sum * 10 + chs[i] - '0';
                i++;
            }

            return sum;
        }

        if (ch != '(') {
            throw new IllegalStateException("first char of next value is " + ch);
        }

        i++;    // skip (
        int res = nextExpression();
        skipSpaces();
        if (i == n || chs[i] != ')') {
            throw new IllegalStateException("after reading value, no matching closing found ");
        }
        i++;    // skip )
        return res;
    }

    void skipSpaces() {
        while (i < n && chs[i] == ' ') {
            i++;
        }
    }

    public static void main(String[] args) {
        System.out.println(new BasicCalc().calculate("1 + 1"));
        System.out.println(new BasicCalc().calculate(" 2-1 + 2 "));
        System.out.println(new BasicCalc().calculate("(1+(4+5+2)-3)+(6+8)"));
    }

}