package com.ssynhtn.mock;

public class Candy {
    public int candyTwo(int[] ratings) {
        int[] candies = new int[ratings.length];
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = Math.max(candies[i], candies[i-1] + 1);
            }
        }

        int sum = ratings.length + candies[ratings.length - 1];
        for (int j = ratings.length - 2; j>= 0; j--) {
            if (ratings[j] > ratings[j + 1]) {
                candies[j] = Math.max(candies[j], candies[j + 1] + 1);
            }

            sum += candies[j];
        }

        return sum;


    }
    public int candy(int[] ratings) {
        int sum = 0;
        int len = ratings.length;

        int i = 0;
        int peak;

        int j;

        while (i < len) {
            j = i + 1;
            while (j < len && ratings[j] > ratings[j - 1]) {
                j++;
            }

            peak = j - 1;
            while (j < len && ratings[j] < ratings[j - 1]) {
                j++;
            }




            int l1 = peak - i + 1;
            int l2 = j - peak;

//            System.out.println("found peak with left len " + l1 + ", right len " + l2);

            sum += l1 * (l1 - 1) / 2 + l2 * (l2 -1) /2 + Math.max(l1, l2);

            if (j < len && ratings[j] > ratings[j - 1]) {
                i = j - 1;
                sum -= 1;
            } else {
                i = j;
            }


        }

        return sum;

    }



    public static void main(String[] args) {
        System.out.println(new Candy().candyTwo(new int[]{1,2,2}));
    }
}
