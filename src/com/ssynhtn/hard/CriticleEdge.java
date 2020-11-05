package com.ssynhtn.hard;

import java.util.*;
import java.util.List;


public class CriticleEdge {
    public List<List<Integer>> criticalConnections2(int n, List<List<Integer>> connections) {
//        make graph, nodes -> nbs
//
//        make two sets of edges: one circle, one critical
//        for each edge(a, b)
//        search from a to b, keep the path
//        if found then add all to circle
//  else add to critical, remove a, b from each other
        Set<Integer>[] nbs = new Set[n];
        for (int i = 0; i < n; i++) {
            nbs[i] = new HashSet<>();
        }
        for (List<Integer> edge : connections) {
            int a = edge.get(0);
            int b = edge.get(1);
            nbs[a].add(b);
            nbs[b].add(a);
        }

        Set<Integer>[] circles = new HashSet[n];
        for (int i = 0; i < n; i++) {
            circles[i] = new HashSet<>();
        }
        List<List<Integer>> criticals = new ArrayList<>();

        for (List<Integer> edge : connections) {
            int a = edge.get(0);
            int b = edge.get(1);
            if (circles[a].contains(b)) continue;

            // temp remove a, b from each other
            nbs[a].remove(b);
            nbs[b].remove(a);

            List<Integer> path = search(nbs, a, b, circles);
            if(path != null) {
                for (int i = 0; i < path.size() - 1; i++) {
                    int x = path.get(i);
                    int y = path.get(i + 1);
                    circles[x].add(y);
                    circles[y].add(x);
                }
                nbs[a].add(b);
                nbs[b].add(a);
            } else {
                criticals.add(edge);
            }
        }


        return criticals;
    }

    private List<Integer> search(Set<Integer>[] nbs, int a, int b, Set<Integer>[] circles) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> prefix = new ArrayList<>();
        visited.add(a);
        prefix.add(a);
        if (search(nbs, prefix, a, b, visited, circles)) {
            return prefix;
        }
        return null;
    }

    // prefix.last == a
    private boolean search(Set<Integer>[] nbs, List<Integer> prefix, int a, int b, Set<Integer> visited, Set<Integer>[] circles) {
        if (a == b) return true;
        Set<Integer> nexts = nbs[a];

        for (int next : nexts) {
            if (visited.contains(next)) {
                // in prefix we have ... next -> .... a
                if (prefix.size() >= 2 && prefix.get(prefix.size() - 2) != next) {
                    circles[a].add(next);
                    circles[next].add(a);
                    for (int i = prefix.size() - 1; i >= 0; i--) {
                        int u = prefix.get(i);
                        int v = prefix.get(i - 1);
                        circles[u].add(v);
                        circles[v].add(u);
                        if (v == next) break;
                    }
                }
                continue;
            }
            visited.add(next);
            prefix.add(next);
            if (search(nbs, prefix, next, b, visited, circles)) {
                return true;
            }
            prefix.remove(prefix.size() - 1);
        }

        return false;
    }


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
//        n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
        List<List<Integer>> res = new CriticleEdge().criticalConnections2(4, Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(0, 2),
                Arrays.asList(0, 3),
                Arrays.asList(1, 2),
                Arrays.asList(1, 3),
                Arrays.asList(2, 3)
        ));

        for (List<Integer> edge : res) {
            System.out.println(new ArrayList<>(edge));
        }
    }
}
