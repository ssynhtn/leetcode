package com.ssynhtn.daily;

import com.sun.javafx.embed.HostDragStartListener;
import org.omg.PortableInterceptor.HOLDING;

import java.util.*;

public class ContainsDuplicateIII {
    static class Holder implements Comparable<Holder> {
        int num;
        long minDiff;

        public Holder(int num) {
            this.num = num;
        }

        @Override
        public int compareTo(Holder o) {
            if (this == o) return 0;
            int diff = this.num - o.num;
            int absDiff = this.num >= o.num ? diff : -diff;
            if (absDiff >= 0) {
                this.minDiff = Math.min(minDiff, absDiff);
                o.minDiff = Math.min(o.minDiff, absDiff);
            }
            return diff;
        }
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length <= 1) return false;
        if (k == 0) return false;

        if (k >= nums.length) {
            k = nums.length - 1;
        }

        // tree set of holder, n -> holder
        ArrayDeque<Holder> holders = new ArrayDeque<>();
        Set<Holder> binaryTree = new TreeSet<>();

        int i;
        for (i = 0; i <= k; i++) {
            Holder holder = new Holder(nums[i]);
            holders.addLast(holder);
            holder.minDiff = Long.MAX_VALUE;

            binaryTree.add(holder);
            if (holder.minDiff <= t) {
//                System.out.println("add at " + i + " of " + holder.num + " leads to diff " + holder.minDiff);
                return true;
            }
        }

        while (i < nums.length) {
            Holder last = holders.removeFirst();
            binaryTree.remove(last);

            Holder holder = new Holder(nums[i]);
            holders.addLast(holder);
            holder.minDiff = Long.MAX_VALUE;

            binaryTree.add(holder);
            if (holder.minDiff <= t) {
//                System.out.println("add at " + i + " of " + holder.num + " leads to diff " + holder.minDiff);
                return true;
            }
            i++;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new ContainsDuplicateIII().containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
    }
}
