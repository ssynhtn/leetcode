package com.ssynhtn.easy;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> last = null;
        for (int r = 0; r < numRows; r++) {
            List<Integer> list = new ArrayList<>(r + 1);
            list.add(1);
            if (r > 0) {
                for (int j= 1; j < r; j++) {
                    list.add(last.get(j - 1) + last.get(j));
                }
                list.add(1);
            }
            res.add(list);
            last = list;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new PascalTriangle().generate(4));
    }
}
