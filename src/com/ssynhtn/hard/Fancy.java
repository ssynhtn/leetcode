package com.ssynhtn.hard;

import com.ssynhtn.easy.HouseRobber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ObjIntConsumer;

class Fancy {
    private static final int N = 1000000007;
    static final int[] negs = {0, 1, 500000004, 333333336, 250000002, 400000003, 166666668, 142857144, 125000001, 111111112, 700000005, 818181824, 83333334, 153846155, 71428572, 466666670, 562500004, 352941179, 55555556, 157894738, 850000006, 47619048, 409090912, 739130440, 41666667, 280000002, 576923081, 370370373, 35714286, 758620695, 233333335, 129032259, 281250002, 939393946, 676470593, 628571433, 27777778, 621621626, 78947369, 717948723, 425000003, 658536590, 23809524, 395348840, 204545456, 822222228, 369565220, 404255322, 520833337, 448979595, 140000001, 784313731, 788461544, 56603774, 685185190, 763636369, 17857143, 385964915, 879310351, 50847458, 616666671, 688524595, 564516133, 15873016, 140625001, 30769231, 469696973, 686567169, 838235300, 579710149, 814285720, 98591550, 13888889, 410958907, 310810813, 93333334, 539473688, 831168837, 858974365, 202531647, 712500005, 123456791, 329268295, 84337350, 11904762, 670588240, 197674420, 252873565, 102272728, 415730340, 411111114, 164835166, 184782610, 43010753, 202127661, 231578949, 760416672, 268041239, 724489801, 646464651, 570000004};
    static Map<Integer, Integer> negMap = new HashMap<>();
    List<Integer> nums = new ArrayList<>();
    int a;
    int b;
    int invA;
    boolean invValid;

    public Fancy() {
        a = 1;
        invA = 1;
        invValid = true;
    }
    
    public void append(int val) {
        if (!invValid) {
            computeInv();
        }

        System.out.println("append " + val);
        nums.add((int) (((long) (val - b) * invA % N + N) % N));
        System.out.println("real store " + nums.get(nums.size() - 1) + ", try get: " + getIndex(nums.size() - 1));
    }

    private void computeInv() {
        if (a <= 100) {
            invA = negs[a];
        } else if (negMap.containsKey(a)) {
            invA = negMap.get(a);
        } else {
            invA = computeNeg(a, N);
        }
        invValid = true;
    }

    // (a, n) = 1, a < n
    private int computeNeg(int a, int n) {
        int[] res = computeLamda(a, n);
        return (res[0] + N) % N;
    }

    private int[] computeLamda(int a, int b) {
        if (a == 1) return new int[]{1, 0};
        if (b == 1) return new int[]{0, 1};
        if (b < a) {
            int[] res = computeLamda(b, a);
            int temp = res[0];
            res[0] = res[1];
            res[1] = temp;
            return res;
        }

        // a < b
        int r = b % a;
        int k = b / a;
        int[] res = computeLamda(r, a);
        int s = res[0];
        int t = res[1];

        res[0] = t - s * k;
        res[1] = s;
        return res;
    }



    private static int power(int a, int n) {
        if (n == 1) return a;

        int s = power(a, n / 2);
        if (n % 2 == 0) {
            return (int) ((long)s * s % N);
        }

        return (int) ((long)s * s % N * a % N);
    }

    public void addAll(int inc) {
        System.out.println("addAll " + inc);
        b = (b + inc) % N;
        printState();
    }

    private void printState() {
        System.out.println("state:");
        System.out.println("nums " + nums);
        if (!invValid) {
            computeInv();
        }
        System.out.println("a " + a + ", b " + b + ", invA " + invA);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            res.add(getIndex(i));
        }
        System.out.println("try get all " + res);
        System.out.println();
    }

    public void multAll(int m) {
        System.out.println("multAll " + m);
        a = (int) ((long) a * m % N);
        b = (int) (((long) b * m) % N);
        invValid = false;

        printState();
    }
    
    public int getIndex(int idx) {
        if (idx >= nums.size()) return -1;
        int x = nums.get(idx);
//        System.out.println(idx + " stores " + x);
        return (int) (((long)x * a + b) % N);
    }

    public static void main(String[] args) {
        String[] actions = {"append","append",
                "getIndex","multAll","append","addAll","append",
                "getIndex","multAll","multAll","addAll","append","multAll","multAll",
                "getIndex","getIndex","getIndex","getIndex","getIndex","append","getIndex","multAll","multAll","multAll","append","getIndex","addAll","append","append","append","getIndex","multAll","multAll","getIndex","addAll","append","append","getIndex","getIndex","getIndex","append","getIndex","multAll","multAll","getIndex","getIndex","getIndex","append","append","getIndex","multAll","append","append","append","getIndex","multAll","multAll","append","getIndex","getIndex","multAll","addAll","multAll","addAll","multAll","getIndex","addAll","getIndex","addAll","addAll","append","multAll","getIndex","getIndex","getIndex","addAll","getIndex"};
        int[] params = {13,8,0,12,3,10,15,2,11,3,14,13,4,4,4,0,1,2,1,11,1,10,11,6,9,0,7,14,9,8,7,7,5,9,15,3,2,11,2,3,13,1,10,4,5,12,12,5,10,7,6,10,3,9,6,5,9,11,9,6,3,14,14,11,3,5,14,15,12,5,1,5,12,0,8,5,6};
        // [null,null,null,13,null,null,null,null,13,null,null,null,null,null,null,208,87872,56192,7088,56192,null,56192,null,null,null,null,57995520,null,null,null,null,14,null,null,280,null,null,null,2,163733060,188126660,null,298035453,null,null,10174400,520,520,null,null,20200,null,null,null,null,138000,null,null,null,3186000,6210000,null,null,null,null,null,133086199,null,57335,null,null,null,null,88455260,805165882,245323246,null,912303244]

        Fancy fancyNeg = new Fancy();
        for (int i = 0; i < actions.length; i++) {
            switch (actions[i]) {
                case "append":
                    fancyNeg.append(params[i]);
                    break;
                case "multAll":
                    fancyNeg.multAll(params[i]);
                    break;
                case "addAll":
                    fancyNeg.addAll(params[i]);
                    break;
                case "getIndex":
                    System.out.println("get at " + params[i] + " -> " + fancyNeg.getIndex(params[i]));


            }
        }
    }
}