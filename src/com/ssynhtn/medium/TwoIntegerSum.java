package com.ssynhtn.medium;

public class TwoIntegerSum {
    public int getSumTwo(int a, int b) {
        while (b != 0) {
            int s = a^b;
            int c = ((a & b) << 1);
            a = s;
            b = c;
        }

        return a;
    }

    public int getSum(int a, int b) {
//        System.out.println("a " + Integer.toBinaryString(a));
//        System.out.println("b " + Integer.toBinaryString(b));
        int s = 0;
        int c = 0;
        int bit = 1;
        while (bit != 0) {
//            System.out.println("bit " + Integer.toBinaryString(bit));
            int x = (a & bit);
            int y = (b & bit);

            if (c != 0 && x != 0 && y != 0 || c != 0 && x == 0 && y == 0 || c == 0 && x != 0 && y == 0 || c == 0 && x == 0 && y != 0) {
                s = s | bit;
//                System.out.println("added bit to s");
            }
//            System.out.println("s " + Integer.toBinaryString(s));
            if (x != 0 && y != 0 || x != 0 && c != 0 || y != 0 && c != 0) {
                c = 1;
            } else {
                c = 0;
            }

//            System.out.println("c " + Integer.toBinaryString(c));
            bit = bit << 1;

        }

        return s;
    }

    public static void main(String[] args) {
        System.out.println(new TwoIntegerSum().getSum(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }
}
