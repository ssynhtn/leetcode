package com.ssynhtn.hard;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CriticleEdge {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        boolean bad = true;
        if (bad) {
            throw new RuntimeException("unfinished");
        }
        Set<Integer>[] graph = makeGraph(n, connections);

        Set<List<Integer>> cyclicEdges = new HashSet<>();
        List<List<Integer>> breakingEdges = new ArrayList<>();

        for (List<Integer> edge : connections) {
            if (cyclicEdges.contains(edge)) continue;

            int from = edge.get(0);
            int to = edge.get(1);

            // remove (from - to) for now
            graph[from].remove(to);
            graph[to].remove(from);

            List<Integer> path = new ArrayList<>();
            path.add(from);
            Set<Integer> visited = new HashSet<>();
            visited.add(from);
            boolean found = findPath(to, path, visited, graph);
            if (found) {
                for (int i = 0; i < path.size() - 1; i++) {
                    List<Integer> e = new ArrayList<>();
                    e.add(path.get(i));
                    e.add(path.get(i + 1));
                    cyclicEdges.add(e);
                    e = new ArrayList<>();
                    e.add(path.get(i + 1));
                    e.add(path.get(i));
                    cyclicEdges.add(e);
                }
                cyclicEdges.add(edge);
                List<Integer> e = new ArrayList<>();
                e.add(edge.get(1));
                e.add(edge.get(0));
                cyclicEdges.add(e);

                // restore (from - to)
                graph[from].add(to);
                graph[to].add(from);
            } else {
                breakingEdges.add(edge);
            }



        }

        return breakingEdges;

    }

    boolean findPath(int to, List<Integer> path, Set<Integer> visited, Set<Integer>[] graph) {
        int last = path.get(path.size() - 1);
        if (last == to) {
            return true;
        }

        for (int next : graph[last]) {
            if (visited.contains(next)) continue;
            path.add(next);
            visited.add(next);
            if (findPath(to, path, visited, graph)) {
                return true;
            }
            path.remove(path.size() - 1);
        }

        return false;
    }

    Set<Integer>[] makeGraph(int n, List<List<Integer>> connections) {
        Set<Integer>[] graph = new Set[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }
        for (List<Integer> edge : connections) {
            int from = edge.get(0);
            int to = edge.get(1);
            graph[from].add(to);
            graph[to].add(from);
        }

        return graph;
    }

    public static void main(String[] args) {

    }
}
