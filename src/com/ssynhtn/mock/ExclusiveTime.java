package com.ssynhtn.mock;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

public class ExclusiveTime {
    private static final char TYPE_DURATION = 'd';
    static class Frame {
        char type;
        int method;
        int t;

        public Frame(char type, int method, int t) {
            this.type = type;
            this.method = method;
            this.t = t;
        }

        @Override
        public String toString() {
            return "Frame{" +
                    "type='" + type + '\'' +
                    ", method=" + method +
                    ", t=" + t +
                    '}';
        }
    }

    public int[] exclusiveTime2(int n, List<String> logs) {
        int[] times = new int[n];
        ArrayDeque<Integer> q = new ArrayDeque<>();



        return times;
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] times = new int[n];
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (String log : logs) {
            String[] tokens = log.split(":");
            int method = Integer.parseInt(tokens[0]);
            char type = tokens[1].charAt(0);
            int t = Integer.parseInt(tokens[2]);


            if (q.isEmpty() || type == 's') {
                q.addLast(t);
//                System.out.println("pushing " + frame);
            } else {
                int duration = 0;
                Integer f;
                while ((f = q.pollLast()) != null && f < 0) {
                    duration -= f;
                }
//                System.out.println("poped durations " + duration);

                if (f == null) throw new IllegalArgumentException();
                times[method] += t - f + 1 - duration;
//                System.out.println("add " + (t - f.t + 1 - duration) + " to " + method);
                if (!q.isEmpty()) {
                    q.addLast(-(t - f + 1));
                }
            }

        }


        return times;

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ExclusiveTime().exclusiveTime(2, Arrays.asList(
                "0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"))));
    }
}
