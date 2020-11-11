package com.ssynhtn.hard;


public class InsertionSortCount {
    private static final int M = 1000000007;
    static class Node {
        int val;
        int lessCount;
        int largeCount;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "[" + val + ", " + lessCount + ", " + largeCount + "]";
        }
    }

    /**
     * [i, j)
     * sort(data, buffer, i, j)
     *     int m = j - i
     *     m <= 1 return
     *     int mid = i + m/2
     *     sort(data, buffer, i, mid)
     *     sort(data, buffer, mid, j)
     *     copy data to buffer from i to j
     *     // two merge, one dry run, update lessCount
     *     // second real merge , update right
     *     // first iteration, when we found two same values, put right first, when put right, lessCount += leftIndex - i
     *     // second iteration, put left first, when put right, rightCount += mid - index
     * for each add min(left, right)
     */
    public int createSortedArray(int[] instructions) {
        int n = instructions.length;
        Node[] data = new Node[n];
        Node[] buffer = new Node[n];
        for (int i = 0; i < n; i++) {
            data[i] = new Node(instructions[i]);
        }

        sort(data, buffer, 0, n);
        long sum = 0;
        for (Node node : data) {
            sum += Math.min(node.lessCount, node.largeCount);
        }

//        System.out.println(Arrays.toString(data));

        return (int) (sum % M);
    }

    private void sort(Node[] data, Node[] buffer, int i, int j) {
        int n = j - i;
        if (n <= 1) return;
        int m = i + n/2;

        sort(data, buffer, i, m);
        sort(data, buffer, m, j);
        System.arraycopy(data, i, buffer, i, n);


        int s = i;
        int t = m;
        int k = i;
        while (s < m && t < j) {
            if (buffer[s].val < buffer[t].val) {
                data[k++] = buffer[s++];
            } else if (buffer[s].val > buffer[t].val) {
                buffer[t].largeCount += m - s;
                buffer[t].lessCount += s - i;
                data[k++] = buffer[t++];
            } else {
                int value = buffer[s].val;
                int s1 = s;
                while (s1 < m && buffer[s1].val == value) {
                    data[k++] = buffer[s1++];
                }

                while (t < j && buffer[t].val == value) {
                    buffer[t].largeCount += m - s1;
                    buffer[t].lessCount += s - i;
                    data[k++] = buffer[t++];
                }

                s = s1;
            }

        }

        while (s < m) {
            data[k++] = buffer[s++];
        }
        while (t < j) {
            buffer[t].lessCount += m - i;
            data[k++] = buffer[t++];
        }
    }

    public static void main(String[] args) {
        System.out.println(new InsertionSortCount().createSortedArray(new int[]{
                1,2,1,2,1,2,1,2,1,2,1,2
        }));
    }
}
