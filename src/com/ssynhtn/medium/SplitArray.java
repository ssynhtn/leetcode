package com.ssynhtn.medium;

import java.util.ArrayDeque;

class SplitArray {
    public boolean isPossible(int[] nums) {
        int n = nums.length;
        int i = 0;
        ArrayDeque<Integer> prev = new ArrayDeque<>();
        ArrayDeque<Integer> curr = new ArrayDeque<>();
        
        Integer last = null;
        while (i < n) {
            if (!prev.isEmpty() && nums[i] != last + 1 && prev.peekLast() < 3) {
                System.out.println("last " + last + " prev " + prev);
                return false;
            }
            

            int x = nums[i];
            while (i < n && nums[i] == x) {
                if (prev.isEmpty()) {
                    curr.addLast(1);
                    System.out.println("new dangling " + x + " at " + i + ", curr " + curr);
                } else {
                    int min = prev.removeLast();
                    min++;
                    curr.addFirst(min);
                    System.out.println("append " + x + " to make len of " + min + ", curr " + curr);
                }
                
                i++;
            }

            if (!prev.isEmpty() && prev.peekLast() < 3) {
                System.out.println("last now " + last + " prev " + prev);
                return false;
            }
            
            last = x;
            ArrayDeque<Integer> temp = prev;
            prev = curr;
            curr = temp;

            curr.clear();

        }


        if (!prev.isEmpty() && prev.peekLast() < 3) {
            System.out.println("last " + last + " curr " + curr);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new SplitArray().isPossible(new int[]{1,2,3,4,4,5}));
    }
}