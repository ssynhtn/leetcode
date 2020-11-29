package com.ssynhtn.contest;

import java.util.ArrayDeque;
import java.util.Arrays;

class Competitive {
    public int[] mostCompetitiveDeque(int[] nums, int k) {
        int n = nums.length;

        // q.size <= k , q.size + remain >= k
        ArrayDeque<Integer> q = new ArrayDeque<>();
        // val < last && q.size - 1 + n - index >= k
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            while (q.size() > 0 && val < q.peekLast() && q.size() - 1 + n - i >= k) {
                q.removeLast();
            }

            if (q.size() < k) {
                q.addLast(val);
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = q.removeFirst();
        }
//

        return res;
    }

    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;

        int[] q = new int[k];
        int size = 0;
        // q.size <= k , q.size + remain >= k
        // val < last && q.size - 1 + n - index >= k
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            while (size > 0 && val < q[size - 1] && size - 1 + n - i >= k) {
                size--;
            }

            if (size < k) {
                q[size++] = val;
            }
        }

        return q;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Competitive().mostCompetitive(new int[]{2,4,3,3,5,4,1,9,6}, 4)));
    }
}