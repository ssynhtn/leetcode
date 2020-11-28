package com.ssynhtn.hard;

import java.util.*;

class FancyList {
//     x y z, w, k


// *a+b, *1+b, *1+0poer

// *a, *a+d

// Map of index -> op index
    
    static final int N = 1000000007;
    static class Op {
        long a = 1;
        long b;

        int end;

        static Op mul(int a) {
            Op op = new Op();
            op.a = a;
            return op;
        }

        static Op add(int b) {
            Op op = new Op();
            op.b = b;
            return op;
        }

        void apply(Op next) {
            a = (a * next.a) % N;
            b = (b * next.a + next.b) % N;
            end = next.end;
        }

        int compute(int x) {
            return (int)((x * a + b) % N);
        }
    }
    
    List<Integer> nums = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    List<Op> ops = new ArrayList<>();

    public FancyList() {

    }
    
    public void append(int val) {
        nums.add(val);
        map.put(nums.size() - 1, ops.size());
    }
    
    public void addAll(int inc) {
        Op add = Op.add(inc);
        ops.add(add);
        add.end = ops.size();
    }
    
    public void multAll(int m) {
        Op mult = Op.mul(m);
        ops.add(mult);
        mult.end = ops.size();

    }
    
    public int getIndex(int idx) {
        if (idx >= nums.size()) return -1;
        int x = nums.get(idx);
        int opIndex = map.get(idx);
        if (opIndex >= ops.size()) return x;
        Op op = ops.get(opIndex);

        if (op == null) return x;

        int n = ops.size();
        prepare(op, n);

        return op.compute(x);
    }

    private void prepare(Op op, int n) {
        if (op.end == n) return;
        Op next = ops.get(op.end);
        prepare(next, n);
        op.apply(next);
    }

    public static void main(String[] args) {
        int[] negs = new int[101];
        negs[1] = 1;
        for (int i = 2; i <= 100; i++) {
            negs[i] = power(i, N-2);
        }

        System.out.println(Arrays.toString(negs));
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