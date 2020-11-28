package com.ssynhtn.easy;

class PerfectNumber {
    public boolean checkPerfectNumber(int num) {
        int product = 1;
        int n = num;
        for (int p = 2; p * p <= n; p++) {
            if (n % p == 0) {
                int temp = 1 + p;
                n = n / p;
                
                while (n % p == 0) {
                    temp = temp * p + 1;
                    n = n / p;
                }
                
                product *= temp;
            }
            
        }

        if (n > 1) {
            // n is prime
            product *= (1 + product);
        }
        

        return product == 2 * num;
    }

    public static void main(String[] args) {
        System.out.println(new PerfectNumber().checkPerfectNumber(2));
    }
}