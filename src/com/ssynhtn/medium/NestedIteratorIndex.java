package com.ssynhtn.medium;

import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIteratorIndex implements Iterator<Integer> {

    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();
        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    final int size;
    ArrayDeque<NestedInteger> stack;
    ArrayDeque<Integer> indexStack;

    final List<NestedInteger> nestedList;

    public NestedIteratorIndex(List<NestedInteger> nestedList) {
        this.nestedList = nestedList != null ? nestedList : new ArrayList<>();
        size = this.nestedList.size();

        stack = new ArrayDeque<>();
        indexStack = new ArrayDeque<>();

        if (size > 0) {
            stack.addLast(this.nestedList.get(0));
            indexStack.addLast(0);

            prepare();
        }

    }

    void prepare() {
        while (!stack.isEmpty() && !stack.peekLast().isInteger()) {
            if (stack.peekLast().getList().size() > 0) {
                stack.addLast(stack.peekLast().getList().get(0));
                indexStack.addLast(0);
            } else {
                stack.removeLast();
                int index = indexStack.removeLast();
                popEndItems(index);
            }
        }
    }

    boolean popEndItems(int index) {
        List<NestedInteger> parentList = stack.isEmpty() ? this.nestedList : stack.peekLast().getList();
        boolean empty = false;
        while (index == parentList.size() - 1) {
            if (stack.isEmpty()) {
                empty = true;
                break;
            }
            stack.removeLast();
            index = indexStack.removeLast();
            parentList = stack.isEmpty() ? this.nestedList : stack.peekLast().getList();
        }

        if (!empty) {
            stack.addLast(parentList.get(index + 1));
            indexStack.addLast(index + 1);
        }

        return empty;
    }

    @Override
    public Integer next() {
        int res = stack.removeLast().getInteger();
        int index = indexStack.removeLast();

        boolean isEmpty = popEndItems(index);
        if (!isEmpty) {
            prepare();
        }

        return res;
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

}