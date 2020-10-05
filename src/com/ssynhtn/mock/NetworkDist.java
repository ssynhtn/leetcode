package com.ssynhtn.mock;

import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLService;
import org.omg.CORBA.INTERNAL;

import java.util.*;

public class NetworkDist {
    static class NodeAndDist implements Comparable<NodeAndDist> {
        int node;
        int dist;

        public NodeAndDist(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(NodeAndDist o) {
            if (this.dist == o.dist) return this.node - o.node;
            return this.dist - o.dist;
        }
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Integer> dist = new HashMap<>();

        Map<Integer, List<int[]>> edges = new HashMap<>();
        for (int[] edge : times) {
            List<int[]> es = edges.computeIfAbsent(edge[0], k -> new ArrayList<>());
            es.add(edge);
        }

        for (Integer node : edges.keySet()) {
            List<int[]> es = edges.get(node);
            if (es != null) {
                es.sort(new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[2] - o2[2];
                    }
                });
            }
        }

        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });


        q.add(new int[]{K, 0});
        while (!q.isEmpty()) {
            int[] nodeDist = q.poll();
            int node = nodeDist[0];
            if (dist.containsKey(node)) continue;

            dist.put(node, nodeDist[1]);
            List<int[]> es = edges.get(node);

            if (es != null) {
                for (int[] edge : es) {
                    q.add(new int[]{edge[1], nodeDist[1] + edge[2]});
                }
            }
        }

//        System.out.println(Arrays.toString(dist));

        if (dist.size() < N) return -1;

        int max = 0;
        for (int d : dist.values()) {

            if (d > max) {
                max = d;
            }
        }
        return max;


    }

    public int networkDelayTimeQ(int[][] times, int N, int K) {
        Map<Integer, Set<int[]>> edges = new HashMap<>();
        for (int[] edge : times) {
            Set<int[]> es = edges.computeIfAbsent(edge[0], k -> new HashSet<>());
            es.add(edge);
        }

        int[] dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[K] = 0;

        Queue<NodeAndDist> q = new ArrayDeque<>();
        q.add(new NodeAndDist(K, 0));

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                NodeAndDist nodeAndD = q.remove();
                int node = nodeAndD.node;
                if (nodeAndD.dist > dist[node]) continue;

                Set<int[]> es = edges.get(node);
                if (es == null) continue;
                for (int[] e : es) {
                    int target = e[1];
                    int newMin = dist[node] + e[2];
                    if (newMin < dist[target]) {
                        dist[target] = newMin;
                        q.add(new NodeAndDist(target, newMin));
                    }
                }
            }
        }

        int maxDist = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            if (dist[i] > maxDist) {
                maxDist = dist[i];
            }
        }
        return maxDist;

    }

    public int networkDelayTime2(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> edges = new HashMap<>();
        for (int[] edge : times) {
            List<int[]> es = edges.computeIfAbsent(edge[0], k -> new ArrayList<>());
            es.add(edge);
        }
        for (int node : edges.keySet()) {
            List<int[]> list = edges.get(node);
            if (list != null) {
                list.sort(new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[2] - o2[2];
                    }
                });
            }
        }

        int[] dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[K] = 0;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(K);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int node = q.remove();

                List<int[]> es = edges.get(node);
                if (es == null) continue;
                for (int[] e : es) {
                    int target = e[1];
                    int newMin = dist[node] + e[2];
                    if (newMin < dist[target]) {
                        dist[target] = newMin;
                        q.add(target);
                    }
                }
            }
        }

        int maxDist = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            if (dist[i] > maxDist) {
                maxDist = dist[i];
            }
        }
        return maxDist;

    }

    public static void main(String[] args) {
        int[][] edges = {
                {1,2,1},
                {2,3,2},
                {1,3,4},
        };
        int N = 3;
        int K = 1;
        System.out.println(new NetworkDist().networkDelayTime(edges, N, K));
    }
}
