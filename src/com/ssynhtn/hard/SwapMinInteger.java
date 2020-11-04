package com.ssynhtn.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SwapMinInteger {
//    static class IndexCount {
//        int index;
//        // 前面比我大的数字的个数
//        int count;
//
//        public IndexCount(int index, int count) {
//            this.index = index;
//            this.count = count;
//        }
//    }
    public String minInteger(String num, int k) {
        char[] chs = num.toCharArray();
        int n = chs.length;
//        System.out.println(Arrays.toString(chs));

        // largerCounts[i]记录比i大的数字的个数
        int[] largerFreqs = new int[10];
        List<Integer>[] largerCounts = new List[10];
        for (int i = 0; i < 10; i++) {
            largerCounts[i] = new LinkedList<>();
        }

        for (int i = 0; i < Math.min(n, k+1); i++) {
            int d = chs[i] - '0';
            largerCounts[d].add(largerFreqs[d]);

            for (int s = 0; s < d; s++) {
                largerFreqs[s]++;
            }
        }

        int i = 0;
        int lIndex = 0;
        while (k > 0) {
            while (i < chs.length) {
                int tlIndex = lIndex;
                while (tlIndex < 10 && (largerCounts[tlIndex].isEmpty() || largerCounts[tlIndex].get(0) > k)) {
                    tlIndex++;
                }

                if (tlIndex == 10) {
                    for (int z = 0; z < 10; z++) {
                        System.out.println(z + " -> " + largerCounts[z]);
                    }

                    System.out.println("chs[i] " + chs[i]);
                    for (int j = lIndex; j < chs[i] - '0'; j++) {
                        List<Integer> temp = largerCounts[j];
                        for (int u = 0; u < temp.size(); u++) {
                            if (temp.get(u) == 1) {
                                temp.remove(u);
                                u--;
                            } else {
                                temp.set(u, temp.get(u) - 1);
                            }
                        }
                    }
                    i++;
                } else {
                    lIndex = tlIndex;
                    break;
                }
            }

            if (i == chs.length) break;

            if (lIndex != chs[i+largerCounts[lIndex].get(0)] - '0') {
                for (int z = 0; z < 10; z++) {
                    System.out.println(z + " => " + largerCounts[z]);
                }
            }
            int larger = largerCounts[lIndex].remove(0);
            System.out.println("move in " + lIndex + "/" + chs[i + larger] + ", to ahead of " + chs[i] + ", used " + larger);

            rotate(chs, i, i + larger);
            System.out.println(Arrays.toString(chs));
            i++;
            k -= larger;
        }

        return new String(chs);
    }

    // j >= i, insert jth to ith
    private void rotate(char[] chs, int i, int j) {
        if (j == i) return;
        char d = chs[j];
        System.arraycopy(chs, i, chs, i + 1, j - i);
        chs[i] = d;
    }

    public static void main(String[] args) {
        System.out.println(new SwapMinInteger().minInteger("294984148179", 11));
    }
}
