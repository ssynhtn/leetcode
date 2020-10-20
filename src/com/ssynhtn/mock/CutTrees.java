package com.ssynhtn.mock;

import java.util.*;

public class CutTrees {
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest.get(0).get(0) == 0) return -1;
//        long start = System.currentTimeMillis();
        int n = forest.size();
        int m = forest.get(0).size();

        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{forest.get(i).get(j), i, j});
                }
            }
        }

        trees.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });


//        System.out.println("trees " + trees);

        int dist = 0;
        int x = 0;
        int y = 0;
        for (int[] next : trees) {
            int nextDist = findDist(x, y, next[1], next[2], forest);
            if (nextDist == -1) return -1;
//            int nextDist = next[3];
//            System.out.println("nextDist to " + next[1] + ", " + next[2] + " of " + forest.get(next[1]).get(next[2])
//                    + " is " + nextDist);
            dist += nextDist;
            x = next[1];
            y = next[2];

        }
//        System.out.println("took " + (System.currentTimeMillis() - start));
        return dist;
    }

    private int findDist(int x, int y, int x2, int y2, List<List<Integer>> forest) {
        int n = forest.size();
        int m = forest.get(0).size();

        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[]{x, y});
        visited[x][y] = true;
        int levels = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                int[] pos = q.poll();
                if (pos[0] == x2 && pos[1] == y2) {
                    return levels;
                }
//                visited[pos[0]][pos[1]] = true;

                int i = pos[0];
                int j = pos[1];
                for (int I = i - 1; I <= i + 1; I = I + 2) {
                    if (I >= 0 && I < n) {
                        if (forest.get(I).get(j) != 0) {
                            if (!visited[I][j]) {
                                visited[I][j] = true;
//                                System.out.println("adding new neigh " + I + ", " + j);
                                q.add(new int[]{I, j});
                            }
                        }
                    }
                }

                for (int J = j - 1; J <= j + 1; J = J + 2) {
                    if (J >= 0 && J < m) {
                        if (forest.get(i).get(J) != 0) {
                            if (!visited[i][J]) {
                                visited[i][J] = true;
//                                System.out.println("adding new neigh " + i + ", " + J);
                                q.add(new int[]{i, J});
                            }
                        }
                    }
                }


            }
            levels++;

        }

//        System.out.println(x + ", " + y + " -> " + x2 + ", " + y2 + ", no dist");
        return -1;
    }

    public static void main(String[] args) {
        int[][] input = {{69438,55243,0,43779,5241,93591,73380},{847,49990,53242,21837,89404,63929,48214},{90332,49751,0,3088,16374,70121,25385},{14694,4338,87873,86281,5204,84169,5024},{31711,47313,1885,28332,11646,42583,31460},{59845,94855,29286,53221,9803,41305,60749},{95077,50343,27947,92852,0,0,19731},{86158,63553,56822,90251,0,23826,17478},{60387,23279,78048,78835,5310,99720,0},{74799,48845,60658,29773,96129,90443,14391},{65448,63358,78089,93914,7931,68804,72633},{93431,90868,55280,30860,59354,62083,47669},{81064,93220,22386,22341,95485,20696,13436},{50083,0,89399,43882,0,13593,27847},{0,12256,33652,69301,73395,93440,0},{42818,87197,81249,33936,7027,5744,64710},{35843,0,99746,52442,17494,49407,63016},{86042,44524,0,0,26787,97651,28572},{54183,83466,96754,89861,84143,13413,72921},{89405,52305,39907,27366,14603,0,14104},{70909,61104,70236,30365,0,30944,98378},{20124,87188,6515,98319,78146,99325,88919},{89669,0,64218,85795,2449,48939,12869},{93539,28909,90973,77642,0,72170,98359},{88628,16422,80512,0,38651,50854,55768},{13639,2889,74835,80416,26051,78859,25721},{90182,23154,16586,0,27459,3272,84893},{2480,33654,87321,93272,93079,0,38394},{34676,72427,95024,12240,72012,0,57763},{97957,56,83817,45472,0,24087,90245},{32056,0,92049,21380,4980,38458,3490},{21509,76628,0,90430,10113,76264,45840},{97192,58807,74165,65921,45726,47265,56084},{16276,27751,37985,47944,54895,80706,2372},{28438,53073,0,67255,38416,63354,69262},{23926,75497,91347,58436,73946,39565,10841},{34372,69647,44093,62680,32424,69858,68719},{24425,4014,94871,1031,99852,88692,31503},{24475,12295,33326,37771,37883,74568,25163},{0,18411,88185,60924,29028,69789,0},{34697,75631,7636,16190,60178,39082,7052},{24876,9570,53630,98605,22331,79320,88317},{27204,89103,15221,91346,35428,94251,62745},{26636,28759,12998,58412,38113,14678,0},{80871,79706,45325,3861,12504,0,4872},{79662,15626,995,80546,64775,0,68820},{25160,82123,81706,21494,92958,33594,5243}};
        List<List<Integer>> data = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < input[i].length; j++) {
                row.add(input[i][j]);
            }
            data.add(row);
//            System.out.println(row);
        }

        System.out.println(new CutTrees().cutOffTree(data));
    }
}
