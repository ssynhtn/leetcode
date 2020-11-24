package com.ssynhtn.medium;

public class Rand10 {
    static int rand7() {
        return (int) (Math.random() * 7) + 1;
    }

    /**
     *
     7*7 = 49
     (6-1)*7 + 7 = 42

     49%10 = 9
     49-9 = 40

     42 > 40  -> 2 / 9

     38 <= 40

     38 % 10 + 1

     */
    static int rand10UseAll() {
        int n = 7;
        int r = rand7();
        while (true) {
            n = n * 7;
            r = (r - 1) * 7 + rand7();

            int m = n % 10;
            int bound = n - m;

            if (r <= bound) {
                return r % 10 + 1;
            }

            r = r - bound;
            n = m;

        }
    }
    int rand10() {
        int a = rand2();
        int b = rand5();
        return b * 2 + a + 1;
    }

    int rand2() {
        int r;
        while ((r = rand7()) == 7) {

        }

        return r % 2;
    }

    int rand5() {
        int r;
        while ((r = rand7()) >= 6) {

        }

        return r - 1;
    }

    public static void main(String[] args) {
        int s = 0;
        for (int i = 0; i < 100; i++) {
            int r = rand10UseAll();
            s += r;
            System.out.print(r + ",");
        }

        System.out.println();
        System.out.println(s / 100.0);
    }
}
