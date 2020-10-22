package com.ssynhtn.mock;

import com.sun.tools.internal.xjc.reader.dtd.TDTDReader;

import java.sql.SQLSyntaxErrorException;
import java.util.Comparator;
import java.util.TreeSet;

public class KthLargest {
//
//    int[] larges;
//    int size;

    static class IntHolder implements Comparable<IntHolder> {
        int val;

        public IntHolder(int val) {
            this.val = val;
        }

        @Override
        public int compareTo(IntHolder o) {
            if (this == o) return 0;
            if (this.val < o.val) return -1;
            if (this.val > o.val) return 1;
            int me = System.identityHashCode(this);
            int them = System.identityHashCode(o);
            return Integer.compare(me, them);
        }
    }

    TreeSet<IntHolder> larges;
    int k;
    public KthLargest(int k, int[] nums) {
        larges = new TreeSet<>();
        this.k = k;

        for (int x : nums) {
            if (larges.size() < k) {
                larges.add(new IntHolder(x));
            } else {
                if (x <= larges.first().val) {
                    continue;
                }

                larges.pollFirst();
                larges.add(new IntHolder(x));
            }
        }
    }
    
    public int add(int val) {
        if (larges.size() < k) {
            larges.add(new IntHolder(val));
            return larges.first().val;
        }

        if (val <= larges.first().val) {
            return larges.first().val;
        }

        larges.pollFirst();
        larges.add(new IntHolder(val));
        return larges.first().val;
    }

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.larges);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.larges);
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.larges);
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.larges);
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.larges);
        System.out.println(kthLargest.add(4));
        System.out.println(kthLargest.larges);
    }

}