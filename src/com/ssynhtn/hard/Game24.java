package com.ssynhtn.hard;

import java.util.*;

class Game24 {
    char[] ops = {'+', '-', '*', '/'};
    public Map<String, Fraction> judgePoint24(int[] nums) {
        Fraction[] fs = new Fraction[nums.length];
        for (int i = 0; i < nums.length; i++) {
            fs[i] = new Fraction(nums[i]);
        }
        List<Fraction> collect = new ArrayList<>();
        can(fs, collect);

        Map<String, Fraction> res = new HashMap<>();
        for (Fraction f : collect) {
            res.put(f.toString(), f);
        }
        return res;
    }

    void can(Fraction[] fs, List<Fraction> collect) {
        int n = fs.length;
        if (n == 1) {
            if (fs[0].is24()) {
                collect.add(fs[0]);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            for (char op : ops) {
                boolean isCommu = op == '+' || op == '*';
                int start = isCommu ? i + 1 : 0;
                for (int j = start; j < n; j++) {
                    if (j == i) continue;

                    if (op == '/' && fs[j].isZero()) continue;

                    Fraction[] buff = new Fraction[n-1];
                    int k = 0;
                    if (isCommu && fs[i].toString().compareTo(fs[j].toString()) < 0) {
                        buff[k++] = fs[j].op(fs[i], op);
                    } else {
                        buff[k++] = fs[i].op(fs[j], op);
                    }

                    for (int l = 0; l < n; l++) {
                        if (l != i && l != j) {
                            buff[k++] = fs[l];
                        }
                    }

                    can(buff, collect);



                }

            }


        }

    }




    static class Fraction {
        int a;
        int b;
        Fraction left;
        Fraction right;
        char op;
        String exp;

        Fraction(int x) {
            a = x;
            b = 1;
            exp = a + "";
        }

        Fraction(int a, int b) {
            this.a = a;
            this.b = b;
        }

        Fraction op(Fraction o, char op) {
            Fraction fraction = null;
            switch (op) {
                case '+':
                    fraction = add(o);
                    break;
                case '-':
                    fraction = sub(o);
                    break;
                case '*':
                    fraction = mul(o);
                    break;
                case '/':
                default:
                    fraction = div(o);
                    break;
            }

            fraction.left = this;
            fraction.right = o;
            fraction.op = op;
            fraction.exp = fraction.computeString();
            return fraction;
        }

        private String computeString() {
            if (left == null) {
                return a + "";
            }

            StringBuilder sb = new StringBuilder();

            if (pre() < left.pre()) {
                sb.append("(");
                sb.append(left);
                sb.append(")");
            } else {
                sb.append(left);
            }
            sb.append(" ");
            sb.append(op);
            sb.append(" ");
            if (pre() < right.pre() || pre() == right.pre() && (op == '/' || op == '-')) {
                sb.append("(");
                sb.append(right);
                sb.append(")");
            } else {
                sb.append(right);
            }

            return sb.toString();
        }

        Fraction add(Fraction o) {
            int c = o.a;
            int d = o.b;
            return new Fraction(a * d + b * c, b * d);
        }

        Fraction mul(Fraction o) {
            return new Fraction(a * o.a, b * o.b);
        }

        Fraction div(Fraction o) {
            return new Fraction(a * o.b, b * o.a);
        }

        Fraction sub(Fraction o) {
            int c = o.a;
            int d = o.b;
            return new Fraction(a * d - b * c, b * d);
        }

        boolean isZero() {
            return a == 0;
        }

        boolean is24() {
            return a == b * 24;
        }

        @Override
        public String toString() {
            return exp;
        }

        int pre() {
            if (left != null) {
                if (op == '*' || op == '/') return 1;
                return 2;
            }

            return 0;
        }

        public boolean hasFraction() {
            if (left == null) return false;
            if (a % b != 0) return true;
            return left.hasFraction() || right.hasFraction();
        }
    }

    public static void main(String[] args) {
        int count = 0;
        int total = 0;
        for (int i = 1; i <= 13; i++) {
            for (int j = i; j <= 13; j++) {
                for (int k = j; k <= 13; k++) {
                    for (int l = k; l <= 13; l++) {
                        total++;
                        int[] nums = new int[]{i, j, k, l};
                        Map<String, Fraction> res = new Game24().judgePoint24(nums);
                        if (res.size() == 1) {
                            Fraction f = res.values().iterator().next();
                            if (f.hasFraction()) {
                                System.out.println(Arrays.toString(nums) + " -> " + f);
                                count++;
                            }
                        }

//                        if (res.isEmpty()) {
//                            count++;
//                            System.out.println(Arrays.toString(nums));
//                        }
                    }
                }
            }
        }

        System.out.println(count + "/" + total);
    }
}