package com.ssynhtn.medium;

import java.util.*;

public class CourseDependency {

//    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
//
////        for each node a
////        if visited continue
////                create short visited set
////        create q of nodes // stack
////        add a to q
////        mark a visited in both sets
////        while q not empty
////        remove x from q
////        for x's neis
////        if one of them is visited in temp, then found cycle
////        for those not visited in either, add to q, mark as visited for both
//
//        List<Integer>[] neibs = new List[numCourses];
//        for (int i = 0; i < numCourses; i++) {
//            neibs[i] = new ArrayList<>();
//        }
//        for (int[] edge : prerequisites) {
//            int a = edge[0];
//            int b = edge[1];
//            neibs[a].add(b);
//        }
//
//        boolean[] visited = new boolean[numCourses];
//
//        ArrayDeque<Integer> stack = new ArrayDeque<>();
//        Set<Integer> marked = new HashSet<>();
//        for (int i = 0; i < numCourses; i++) {
//            if (visited[i]) continue;
//
//            stack.addLast(i);
//            marked.add(i);
//            visited[i] = true;
//
//            while (!stack.isEmpty()) {
//                int a = stack.removeLast();
//                for (int b : neibs[a]) {
//                    if (marked.contains(b)) {
//                        return false;
//                    }
//
//                    if (visited[b]) continue;
//
//                    stack.addLast(b);
//                    marked.add(b);
//                    visited[b] = true;
//                }
//            }
//
//            marked.clear();
//        }
//
//        return true;
//    }


//    each -> depends
//
//    start with empty set
//
//for each remaining remove its dependency on found nodes
//
//if after removing it is empty, keep it,
//else add back to q
//for those now empty, add it to the consistent nodes

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] dependsOn = new int[numCourses]; // # of courses that i depends on
        List<Integer>[] dependedBy = new List[numCourses];  // list of numbers that i is depended by

        for (int i = 0; i < numCourses; i++) {
            dependedBy[i] = new ArrayList<>();
        }

        for (int[] dep : prerequisites) {
            int a = dep[0];
            int b = dep[1];
            // a depends on b
            dependsOn[a]++;
            dependedBy[b].add(a);
        }

        ArrayDeque<Integer> canTake = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (dependsOn[i] == 0) {
                canTake.addLast(i);
            }
        }

        int rem = numCourses;
        while (rem > 0) {
            int size = canTake.size();
            if (size == 0) return false;

            rem -= size;
            while (size > 0){
                size--;
                int c = canTake.removeFirst();
                for (int d : dependedBy[c]) {
                    dependsOn[d]--;
                    if (dependsOn[d] == 0) {
                        canTake.addLast(d);
                    }
                }
            }

        }

        return true;
    }


    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] dependsOn = new int[numCourses]; // # of courses that i depends on
        List<Integer>[] dependedBy = new List[numCourses];  // list of numbers that i is depended by

        for (int i = 0; i < numCourses; i++) {
            dependedBy[i] = new ArrayList<>();
        }

        for (int[] dep : prerequisites) {
            int a = dep[0];
            int b = dep[1];
            // a depends on b
            dependsOn[a]++;
            dependedBy[b].add(a);
        }

        int[] canTake = new int[numCourses];
        int start = 0;
        int size = 0;
        for (int i = 0; i < numCourses; i++) {
            if (dependsOn[i] == 0) {
                canTake[size++] = i;
            }
        }

        while (size < numCourses) {
            if (start == size) return new int[0];

            int nowSize = size;
            while (start < nowSize){
                int c = canTake[start++];
                for (int d : dependedBy[c]) {
                    dependsOn[d]--;
                    if (dependsOn[d] == 0) {
                        canTake[size++] = d;
                    }
                }
            }

        }

        return canTake;
    }

    public static void main(String[] args) {
        System.out.println(new CourseDependency().canFinish(3, new int[][]{
                {0, 1},
                {0, 2},
                {1, 2}
        }));
    }
}
