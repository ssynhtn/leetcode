package com.ssynhtn.contest;

import java.util.Arrays;

public class MaxProfit {
    static final int M = 1000000007;
    public int maxProfit(int[] inventory, int orders) {
        long income = 0;
        Arrays.sort(inventory);
        int n = inventory.length;
        int i = n - 1;

        while (orders > 0) {
            while (i - 1 >= 0 && inventory[i-1] == inventory[i]) {
                i--;
            }

            // sell colors from i to n-1
            int width = n - i;
            int height = i > 0 ? inventory[i] - inventory[i-1] : inventory[i];
            int h = orders / width;

            int firstLevel = inventory[i];
            int soldH = Math.min(h, height);
            int lastLevel = firstLevel - soldH + 1;
            long temp = ((firstLevel + lastLevel) * (long)soldH % M) * width % M;
            if (temp % 2 != 0) {
                temp = temp + M;
            }
            income = (income + temp / 2) % M;
//            System.out.println("temp " + temp + ", income " + income);
//             income += ((firstLevel + lastLevel) * (long)soldH) * width / 2;

            // System.out.println("height " + height + ", h " + h  +" soldH " + soldH + ", income " + income + ", orders " + orders);
            if (height > h || (height == h && orders % width == 0)) {
                income += (soldH == 0 ? firstLevel : (lastLevel - 1)) * ((long)orders % width);
                income = income % M;
                return (int)income;
            }

            i--;
            orders -= width * soldH;


        }

        return (int)income;
    }


    public static void main(String[] args) {
        System.out.println(new MaxProfit().maxProfit(new int[]{
                565259708,715164401,716563713,958255469,844600740,823949511,180479359,287829385,164248818,73361150,230686692,322986846,598720034,338241127,748922260,181241085,833659853,509571179,250093451,690995620,703292727,595636202
        }, 650114768));
    }
}
