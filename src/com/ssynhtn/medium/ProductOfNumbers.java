package com.ssynhtn.medium;

import java.util.ArrayList;
import java.util.List;

public class ProductOfNumbers {
    List<Integer> pros = new ArrayList<>();

    public ProductOfNumbers() {

    }

    public void add(int num) {
        if (num > 0) {
            if (pros.size() > 0) {
                pros.add(pros.get(pros.size() - 1) * num);
            } else {
                pros.add(num);
            }
        } else {
            pros.clear();
        }
    }

    public int getProduct(int k) {
        if (k > pros.size()) {
            return 0;
        }
        if (k == pros.size()) {
            return pros.get(k - 1);
        }

        return pros.get(pros.size() - 1) / pros.get(pros.size() - 1 - k);
    }
}
