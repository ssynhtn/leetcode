package com.ssynhtn.contest;

class SmallString {
//    aaaaaaczzzzzz
//
//    a + 1 + z = n
//    a + c + 26z = k
//
//    c in 1to26
//
//    c-1 +25z = k-n
//
//    c-1 in 0 to 25
//
//    c-1 = k-n % 25
//
//    z = k-n / 25
//    a = n - 1 - z
    public String getSmallestString(int n, int k) {
        int m = k - n;
        int z = m / 25;
        int a = n - 1 - z;
        int c = m % 25;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a; i++) {
            sb.append('a');
        }
        sb.append((char)(c + 'a'));
        for (int i = 0; i < z; i++) {
            sb.append('z');
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        System.out.println(new SmallString().getSmallestString(24, 552));

    }
}