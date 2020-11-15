package com.ssynhtn.medium;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;

public class NestedIteratorRightToLeft implements Iterator<Integer> {

    ArrayDeque<NestedIteratorIndex.NestedInteger> stack = new ArrayDeque<>();
    public NestedIteratorRightToLeft(List<NestedIteratorIndex.NestedInteger> nestedList) {
        if (nestedList != null) {
            int n = nestedList.size();
            for (int i = n-1; i>=0; i--) {
                stack.addLast(nestedList.get(i));
            }
        }
        
        prepare();
        
    }
    void prepare() {
        while (!stack.isEmpty() && !stack.peekLast().isInteger()) {
            List<NestedIteratorIndex.NestedInteger> children = stack.removeLast().getList();
            int m = children.size();
            for (int i = m-1; i>= 0; i--) {
                stack.add(children.get(i));
            }
        }
    }

    @Override
    public Integer next() {
        int res = stack.removeLast().getInteger();
        prepare();
        return res;
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
