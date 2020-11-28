package com.ssynhtn.hard;

import java.util.*;

class FancyNeg {
//     x y z, w, k


// *a+b, *1+b, *1+0

// *a, *a+d

// Map of index -> op index

    static final int N = 1000000007;
    static final int[] negs = {0, 1, 500000004, 333333336, 250000002, 400000003, 166666668, 142857144, 125000001, 111111112, 700000005, 818181824, 83333334, 153846155, 71428572, 466666670, 562500004, 352941179, 55555556, 157894738, 850000006, 47619048, 409090912, 739130440, 41666667, 280000002, 576923081, 370370373, 35714286, 758620695, 233333335, 129032259, 281250002, 939393946, 676470593, 628571433, 27777778, 621621626, 78947369, 717948723, 425000003, 658536590, 23809524, 395348840, 204545456, 822222228, 369565220, 404255322, 520833337, 448979595, 140000001, 784313731, 788461544, 56603774, 685185190, 763636369, 17857143, 385964915, 879310351, 50847458, 616666671, 688524595, 564516133, 15873016, 140625001, 30769231, 469696973, 686567169, 838235300, 579710149, 814285720, 98591550, 13888889, 410958907, 310810813, 93333334, 539473688, 831168837, 858974365, 202531647, 712500005, 123456791, 329268295, 84337350, 11904762, 670588240, 197674420, 252873565, 102272728, 415730340, 411111114, 164835166, 184782610, 43010753, 202127661, 231578949, 760416672, 268041239, 724489801, 646464651, 570000004};
    static class Op {
        int a = 1;
        int b;

        static Op mul(int a) {
            Op op = new Op();
            op.a = a;
            return op;
        }

        @Override
        public String toString() {
            return "*" + a + "+" + b;
        }

        static Op add(int b) {
            Op op = new Op();
            op.b = b;
            return op;
        }

        Op mulBy(int m) {
            Op op = new Op();
            op.a = (int) (1L * this.a * m % N);
            op.b = (int) (1L * this.b * m % N);
            return op;
        }

        Op addBy(int b) {
            Op op = new Op();
            op.a = this.a;
            op.b = (this.b + b) % N;
            return op;
        }

        static int compute(Op next, Op op, int x) {
            int a = op.a;
            int b = op.b;
            int e = next.a;
            int f = next.b;

            int nega = a <= 100 ? negs[a] : power(a, N-2);
            System.out.println("ab ef" + a + ", " + b + ", " + e + ", " + f);
            System.out.println("negs " + a + " -> " + nega);
            int c = (int) ((1L * e * nega) % N);
            int d = (int) (((f - 1L * b * c) % N + N) % N);
            System.out.println("c " + c + " d " + d);

            return (int) ((1L * x * c + d) % N);
        }

        int compute(int x) {
            return (int)((1L * x * a + b) % N);
        }


    }

    List<Integer> nums = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    List<Op> ops = new ArrayList<>();

    public FancyNeg() {

    }
    
    public void append(int val) {
        System.out.println("append " + val);
        nums.add(val);
        map.put(nums.size() - 1, ops.size());
    }
    
    public void addAll(int inc) {
        System.out.println("add all " + inc);
        if (ops.isEmpty()) {
            ops.add(Op.add(inc));
        } else {
            ops.add(ops.get(ops.size() - 1).addBy(inc));
        }

    }
    
    public void multAll(int m) {
        System.out.println("mulAll " + m);
        if (ops.isEmpty()) {
            ops.add(Op.mul(m));
        } else {
            ops.add(ops.get(ops.size() - 1).mulBy(m));
        }

    }

    // size - 1 / opIndex - 1
    public int getIndex(int idx) {
        if (idx >= nums.size()) return -1;
        int x = nums.get(idx);
        int size = ops.size();
        if (size == 0) return x;
        int opIndex = map.get(idx);
        if (opIndex == size) return x;
        if (opIndex == 0) {
            return ops.get(size - 1).compute(x);
        }

        return Op.compute(ops.get(size - 1), ops.get(opIndex - 1), x);
    }

    void printState() {
        System.out.println("state:");
        System.out.println("data " + nums);
        System.out.println("ops " + ops);
        System.out.println();
    }


    public static void main(String[] args) {
        String[] actions = {"append","append","getIndex","multAll","append","addAll","append","getIndex","multAll","multAll","addAll","append","multAll","multAll","getIndex","getIndex","getIndex","getIndex","getIndex","append","getIndex","multAll","multAll","multAll","append","getIndex","addAll","append","append","append","getIndex","multAll","multAll","getIndex","addAll","append","append","getIndex","getIndex","getIndex","append","getIndex","multAll","multAll","getIndex","getIndex","getIndex","append","append","getIndex","multAll","append","append","append","getIndex","multAll","multAll","append","getIndex","getIndex","multAll","addAll","multAll","addAll","multAll","getIndex","addAll","getIndex","addAll","addAll","append","multAll","getIndex","getIndex","getIndex","addAll","getIndex"};
        int[] params = {13,8,0,12,3,10,15,2,11,3,14,13,4,4,4,0,1,2,1,11,1,10,11,6,9,0,7,14,9,8,7,7,5,9,15,3,2,11,2,3,13,1,10,4,5,12,12,5,10,7,6,10,3,9,6,5,9,11,9,6,3,14,14,11,3,5,14,15,12,5,1,5,12,0,8,5,6};

        FancyNeg fancyNeg = new FancyNeg();
        for (int i = 0; i < actions.length; i++) {
            switch (actions[i]) {
                case "append":
                    fancyNeg.append(params[i]);
                    fancyNeg.printState();
                    break;
                case "multAll":
                    fancyNeg.multAll(params[i]);
                    fancyNeg.printState();
                    break;
                case "addAll":
                    fancyNeg.addAll(params[i]);
                    fancyNeg.printState();
                    break;
                case "getIndex":
                    System.out.println("get at " + params[i] + " -> " + fancyNeg.getIndex(params[i]));


            }
        }

//        int[] negs = new int[101];
//        negs[1] = 1;
//        for (int i = 2; i <= 100; i++) {
//            negs[i] = power(i, N-2);
//        }
//
//        System.out.println(Arrays.toString(negs));
    }

    private static int power(int a, int n) {
        if (n == 1) return a;

        int s = power(a, n / 2);
        if (n % 2 == 0) {
            return (int) (1L * s * s % N);
        }

        return (int) (1L * s * s % N * a % N);
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */