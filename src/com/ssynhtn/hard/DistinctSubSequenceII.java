package com.ssynhtn.hard;

import java.util.Arrays;

public class DistinctSubSequenceII {
    static final int M = 1000000007;

    public int distinctSubseq(String S) {
        final int n = S.length();
        char[] chs = S.toCharArray();
        int[] maxIndex = new int[26];
        int[] lastIndex = new int[n];   // lastIndex[i] : last index s.t. chs[index] == chs[i]
        Arrays.fill(lastIndex, -1);
        Arrays.fill(maxIndex, -1);

        for (int i = 0; i < n; i++) {
            int ch = chs[i] - 'a';
            lastIndex[i] = maxIndex[ch];
            maxIndex[ch] = i;
        }


//        for (int[] temp : lastIndex) {
//            System.out.println(Arrays.toString(temp));
//        }

        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int lastI = lastIndex[i-1];
            dp[i] = dp[i-1] * 2;
            if (lastI == -1) {
                dp[i] = dp[i] % M;
            } else if (dp[i] < dp[lastI]) {
                dp[i] = (dp[i] + (M - dp[lastI])) % M;
            } else {
                dp[i] = (dp[i] - dp[lastI]) % M;
            }
        }

        System.out.println(Arrays.toString(dp));

        return dp[n] == 0 ? M-1 : dp[n] - 1;

    }

    public int distinctSubseqIIQuick(String S) {
        final int n = S.length();
        char[] chs = S.toCharArray();
        int[] maxIndex = new int[26];
        int[] lastIndex = new int[n];   // lastIndex[i] : last index s.t. chs[index] == chs[i]
        Arrays.fill(lastIndex, -1);
        Arrays.fill(maxIndex, -1);

        for (int i = 0; i < n; i++) {
            int ch = chs[i] - 'a';
            lastIndex[i] = maxIndex[ch];
            maxIndex[ch] = i;
        }


//        for (int[] temp : lastIndex) {
//            System.out.println(Arrays.toString(temp));
//        }

        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i = 1; i < n; i++) {
            int lastI = lastIndex[i-1];
            dp[i+1] = dp[i] * 2;
            if (dp[i+1] < dp[lastI + 1]) {
                dp[i+1] = (dp[i+1] + (M - dp[lastI + 1])) % M;
            } else {
                dp[i+1] = (dp[i+1] - dp[lastI + 1]) % M;
            }
        }

        System.out.println(Arrays.toString(dp));

        int lastI = lastIndex[n-1];
        int res = dp[n] * 2;
        if (res < dp[lastI + 1]) {
            res = (res + (M - dp[lastI + 1])) % M;
        } else {
            res = (res - dp[lastI + 1]) % M;
        }
        return res == 0 ? M - 1 : res - 1;

    }

    public int distinctSubseqII(String S) {
        final int n = S.length();
        char[] chs = S.toCharArray();
        int[][] lastIndex = new int[n + 1][26];
        for (int[] temp : lastIndex) {
            Arrays.fill(temp, -1);
        }

        for (int i = 0; i < n; i++) {
            int ch = chs[i] - 'a';
            for (int len = i + 1; len <= n; len++) {
                lastIndex[len][ch] = Math.max(lastIndex[len][ch], i);
            }
        }

//        for (int[] temp : lastIndex) {
//            System.out.println(Arrays.toString(temp));
//        }

        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int sum = 1;
            for (int ch = 0; ch < 26; ch++) {
                int lastI = lastIndex[i-1][ch];
                if (lastI >= 0) {
                    sum += dp[lastI+1];
                    sum = sum % M;
                }
            }
            dp[i] = sum;
        }

        System.out.println(Arrays.toString(dp));

        int sum = 0;
        for (int ch = 0; ch < 26; ch++) {
            int lastI = lastIndex[n][ch];
            if (lastI >= 0) {
                sum += dp[lastI+1];
                sum = sum % M;
            }
        }
        return sum;

    }

    public static void main(String[] args) {
        System.out.println(new DistinctSubSequenceII().distinctSubseq("ajxjagdwzxxehvwbxhenrxtoydfobqrlugeuklytwonkrilsthwokzobvtraitboxlsazxstwnjnwnouzuzsskwteuapmmyexvdj"));

        System.out.println(new DistinctSubSequenceII().distinctSubseq("hellobobscot"));
        System.out.println(new DistinctSubSequenceII().distinctSubseqII("hellobobscot"));
        System.out.println(new DistinctSubSequenceII().distinctSubseqIIQuick("abc"));
        System.out.println(new DistinctSubSequenceII().distinctSubseqII("abc"));
        System.out.println(new DistinctSubSequenceII().distinctSubseqIIQuick("aba"));
        System.out.println(new DistinctSubSequenceII().distinctSubseqII("aba"));
        System.out.println(new DistinctSubSequenceII().distinctSubseqIIQuick("aba"));
        System.out.println(new DistinctSubSequenceII().distinctSubseqII("aba"));
    }
    public int distinctSubseqIIN2(String S) {
//         dp[i, j]: for len == i substring, the #of distinct sub sequence that end at j
// i = 1 to n, 0<=j<i

// maxIndex[i][ch]: for len = i substring, the last index of char ch
// i = 0 to n
// init as -1[j];

// dp[i, j], ch=chs[j], if maxIndex[i][ch] == j (i=j+1ok), then dp[i][j] = 1 + dp[j][j-1] + .. dp[j][0] 
// else 0

// dp[1][0] = 1

        int n = S.length();
        char[] chs = S.toCharArray();
        int[][] maxIndex = new int[n+1][26];
        
        for (int[] temp : maxIndex) {
            Arrays.fill(temp, -1);
        }
        
        for (int j = 0; j < n; j++) {
            int ch = chs[j] - 'a';
            for (int i = j + 1; i <= n; i++) {
                maxIndex[i][ch] = Math.max(maxIndex[i][ch], j);
            }
        }
        // for (int[] temp : maxIndex) {
        //     System.out.println(Arrays.toString(temp));
        // }
        
        int[][] dp = new int[n+1][n];

        
        for (int j = 0; j < n; j++) {
            int ch = chs[j]  - 'a';
            int sum = 1;
            if (j > 0) {
                for (int k = 0; k < j; k++) {
                    sum += dp[j][k];
                    sum = sum % M;
                }
            }
            
            
            dp[j+1][j] = sum;
            int i = j + 2;
            while (i <= n && maxIndex[i][ch] == j) {
                dp[i][j] = sum;
                i++;
            }
            
        }
        
        int sum = 0;
        for (int j = 0; j < n; j++) {
            sum += dp[n][j];
            sum = sum % M;
        }
        
        for (int[] temp : dp) {
            System.out.println(Arrays.toString(temp));
        }
        return sum;
    }
}