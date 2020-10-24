package com.ssynhtn.challenge;

import org.omg.CORBA.NO_IMPLEMENT;

import java.lang.reflect.Array;
import java.util.*;

public class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("node ").append(val).append(", hash ").append(System.identityHashCode(this)).append(" => ");
            for (Node x : neighbors) {
                sb.append(x.val).append(",");
            }
            return sb.toString();
        }
    }


    public Node cloneGraph(Node node) {
        Map<Node, Node> cache = new HashMap<>();
        return cloneGraphRec(node, cache);
    }

    public Node cloneGraphRec(Node node, Map<Node, Node> cache) {
        if (node == null) return null;
        Node c = cache.get(node);

//        if (c != null) return c;
//
//        c = new Node();
//        c.val = node.val;
//        cache.put(node, c);
//
//        if (node.neighbors == null) return c;

        if (c == null) {
            c = new Node();
            c.val = node.val;
            cache.put(node, c);
        }

        if (node.neighbors == null) return c;
        if (c.neighbors != null) return c;

        c.neighbors = new ArrayList<>(node.neighbors.size());
        for (Node x : node.neighbors) {
            c.neighbors.add(cloneGraphRec(x, cache));
        }

        return c;
    }

    public Node cloneGraphBFS(Node node) {
        if (node == null) return null;

        ArrayDeque<Node> q = new ArrayDeque<>();
        Map<Node, Node> map = new HashMap<>();

        Node c = new Node();
        c.val = node.val;
        q.add(node);
        map.put(node, c);

        while (!q.isEmpty()) {
            Node x = q.pollLast();
            if (x.neighbors == null) continue;

            Node xc = map.get(x);
            xc.neighbors = new ArrayList<>(x.neighbors.size());
            for (Node y : x.neighbors) {
                Node yc = map.get(y);
                if (yc == null) {
                    yc = new Node();
                    yc.val = y.val;
                    map.put(y, yc);
                    q.add(y);
                }
                xc.neighbors.add(yc);
            }

        }


        return c;
    }

    public Node cloneGraphBFSTrick(Node node) {
        if (node == null) return null;

        Queue<Node> q = new ArrayDeque<>();
        List<Node> origs = new ArrayList<>();

        Node c = new Node();
        c.val = node.val;
        q.add(node);
        map(node, c);
        origs.add(node);

        while (!q.isEmpty()) {
            Node x = q.poll();
            if (x.neighbors.size() == 2) continue;

            Node xc = x.neighbors.get(x.neighbors.size() - 1);
            xc.neighbors = new ArrayList<>(x.neighbors.size() - 2);
            for (int i = 0; i < x.neighbors.size() - 2; i++) {
                Node y = x.neighbors.get(i);
                Node yc = getMap(y);
                if (yc == null) {
                    yc = new Node();
                    yc.val = y.val;
                    map(y, yc);
                    q.add(y);
                    origs.add(y);
                }
                xc.neighbors.add(yc);
            }


        }

        for (Node x : origs) {
            x.neighbors.remove(x.neighbors.size() - 1);
            x.neighbors.remove(x.neighbors.size() - 1);
        }
        return c;
    }

    private void map(Node node, Node c) {
        if (node.neighbors == null) {
            node.neighbors = new ArrayList<>();
        }
        node.neighbors.add(null);
        node.neighbors.add(c);
    }

    private Node getMap(Node y) {
        if (y.neighbors != null && y.neighbors.size() >= 2 && y.neighbors.get(y.neighbors.size() - 2) == null) {
            return y.neighbors.get(y.neighbors.size() - 1);
        }
        return null;
    }

    public static void main(String[] args) {
        Node _1 = new Node(1);
        Node _2 = new Node(2);
        Node _3 = new Node(3);
        Node _4 = new Node(4);

        _1.neighbors.add(_2);
        _1.neighbors.add(_4);
        _2.neighbors.add(_1);
        _2.neighbors.add(_3);
        _3.neighbors.add(_2);
        _3.neighbors.add(_4);
        _4.neighbors.add(_1);
        _4.neighbors.add(_3);

        System.out.println(_1);
//        System.out.println(_2);
//        System.out.println(_3);
//        System.out.println(_4);

        Node c = new CloneGraph().cloneGraph(_1);
        traverse(_1);
        traverse(c);

    }

    private static void traverse(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        q.add(node);
        visited.add(node);

        while (!q.isEmpty()) {
            Node x = q.poll();
            System.out.println(x);
            for (Node y : x.neighbors) {
                if (!visited.contains(y)) {
                    q.add(y);
                    visited.add(y);
                }
            }
        }
    }
}
