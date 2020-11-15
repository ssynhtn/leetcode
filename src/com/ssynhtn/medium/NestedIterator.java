package com.ssynhtn.medium;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {

    LinkedList<NestedIteratorIndex.NestedInteger> list;
    public NestedIterator(List<NestedIteratorIndex.NestedInteger> nestedList) {
        list = new LinkedList<>();
        if (nestedList != null) {
            list.addAll(nestedList);
        }

        prepare();
    }

    private void prepare() {
        while (!list.isEmpty() && !list.get(0).isInteger()) {
            List<NestedIteratorIndex.NestedInteger> cs = list.removeFirst().getList();
            int n = cs.size();
            for (int i = n-1; i >= 0; i--) {
                list.addFirst(cs.get(i));
            }
        }
    }

    @Override
    public Integer next() {
        int res = list.removeFirst().getInteger();
        prepare();
        return res;
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }
}
