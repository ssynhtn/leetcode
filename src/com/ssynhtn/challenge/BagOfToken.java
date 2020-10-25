package com.ssynhtn.challenge;

import java.util.Arrays;
import java.util.Enumeration;

public class BagOfToken {
    public int bagOfTokensScore(int[] tokens, int P) {
        if (tokens.length == 0) return 0;
        Arrays.sort(tokens);
        int s = 0;
        int i = 0, j = tokens.length - 1;

        while (i < j) {
            if (P >= tokens[i]) {
                P -= tokens[i++];
                s++;
            } else if (s > 0) {
                P += tokens[j--];
                s--;
            } else {
                break;
            }
        }

        if (P >= tokens[i]) {
//            P -= tokens[i++];
            s++;
        }

        return s;
    }

    public static void main(String[] args) {
        System.out.println(new BagOfToken().bagOfTokensScore(new int[]{1,2,3,4}, 2));
    }
}
