package com.ssynhtn.easy;

public class RestoreString {
    public String restoreString(String s, int[] indices) {
        char[] chs = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (indices[i] == -1) continue;
            int j = i;

            char ch = chs[j];
            while (true) {
                int next = indices[j];
                char nextCh = chs[next];
                chs[next] = ch;
                indices[j] = -1;



                j = next;
                ch = nextCh;

                if (j == i) break;
            }

        }

        return new String(chs);
    }


    public static void main(String[] args) {
        System.out.println(new RestoreString().restoreString("codeleet", new int[]{4,5,6,7,0,2,1,3}));
    }
}