package com.ssynhtn.contest;

public class CountSubStr {
    public int countSubstrings(String s, String t) {
        char[] as = s.toCharArray();
        char[] bs = t.toCharArray();

        int count = 0;
        int n = as.length;
        int m = bs.length;

        for (int i = 0; i < n; i++) {
            char a = as[i];
            for (int j = 0; j < m; j++) {
                char b = bs[j];
                if (a != b) {
                    int i1 = i-1;
                    int j1 = j-1;
                    while (i1 >= 0 && j1 >= 0 && as[i1] == bs[j1]) {
                        i1--;
                        j1--;
                    }

                    int i2 = i + 1;
                    int j2 = j + 1;
                    while (i2 < n && j2 < m && as[i2] == bs[j2]) {
                        i2++;
                        j2++;
                    }

                    count += (i - i1) * (i2 - i);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new CountSubStr().countSubstrings("ab", "bb"));
    }
}
