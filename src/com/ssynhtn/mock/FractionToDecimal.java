package com.ssynhtn.mock;

import org.omg.CORBA.ARG_IN;

import java.util.*;

public class FractionToDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder sb = new StringBuilder();
        if (numerator > 0 && denominator < 0 || numerator < 0 && denominator > 0) {
            sb.append("-");
        }

        long r = Math.abs((long)numerator);
        long x = Math.abs((long)denominator);

        long y = r / x;
        r = r % x;
        sb.append(y);

//        while r not 0 and r not seen
//        add r to seen
//        r * 10 / x, add to sb, update r
//
//        if r is 0, return sb
//        get index of r in list, get size of [r end), this part is what repeats
        if (r != 0) {
            sb.append(".");

            Set<Long> seen = new HashSet<>();
            List<Long> rs = new ArrayList<>();

            while (r != 0 && !seen.contains(r)) {
                seen.add(r);
                rs.add(r);

                r = r * 10;
                sb.append(r / x);
                r = r % x;
            }

            if (r != 0) {
                int index = rs.indexOf(r);
                int repeatLen = rs.size() - index;
                sb.insert(sb.length() - repeatLen, "(");
                sb.append(")");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new FractionToDecimal().fractionToDecimal(5, 3));
    }
}
