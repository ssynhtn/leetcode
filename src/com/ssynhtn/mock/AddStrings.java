package com.ssynhtn.mock;


import java.util.ArrayDeque;
import java.util.Arrays;

public class AddStrings {
    public String addStrings(String num1, String num2) {
        if (num1.equals("0")) return num2;
        if (num2.equals("0")) return num1;

        char[] xs = num1.toCharArray();
        char[] ys = num2.toCharArray();

        int i = xs.length - 1;
        int j = ys.length - 1;

        char[] zs = new char[Math.max(xs.length, ys.length) + 1];
        int k = zs.length - 1;
        int c = 0;

//        System.out.println("xs " + Arrays.toString(xs));
//        System.out.println("xs " + Arrays.toString(ys));
        while (i >= 0 || j >= 0) {
            c += (i>= 0 ? xs[i] - '0' : 0) + (j >= 0 ? ys[j] - '0' : 0);
//            System.out.println("c " + c);
            zs[k] = (char) ('0' + (c % 10));
            if (c >= 10) {
                c = 1;
            } else {
                c = 0;
            }

            i--;
            j--;
            k--;
        }

        if (c > 0) {
            zs[k] = (char) ('0' + c);
            k--;
        }

        System.out.println(Arrays.toString(zs));

        return new String(zs, k+1, zs.length - k - 1);

    }

    public static void main(String[] args) {
        System.out.println(new AddStrings().addStrings("12", "2"));
    }
}
