package com.ssynhtn.hard;


public class InsertionSortCountBit {
    private static final int M = 1000000007;
    private static final int LESS_SHIFT = 17;
    private static final long VAL_MASK = (1L << LESS_SHIFT) - 1;
    private static final long LESS_MASK = VAL_MASK << LESS_SHIFT;
    private static final int LARGE_SHIFT = LESS_SHIFT * 2;
    private static final long LARGE_MASK = VAL_MASK << LARGE_SHIFT;
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
        long[] data = new long[n];
        long[] buffer = new long[n];
        for (int i = 0; i < n; i++) {
            data[i] = instructions[i];
        }

        sort(data, buffer, 0, n);
        long sum = 0;
        for (long node : data) {
            sum += Math.min((node & LESS_MASK) >> LESS_SHIFT, (node & LARGE_MASK) >> LARGE_SHIFT);
        }

//        System.out.println(Arrays.toString(data));

        return (int) (sum % M);
    }

    private void sort(long[] data, long[] buffer, int i, int j) {
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
            if ((buffer[s] & VAL_MASK) < (buffer[t] & VAL_MASK)) {
                data[k++] = buffer[s++];
            } else if ((buffer[s] & VAL_MASK) > (buffer[t] & VAL_MASK)) {
                buffer[t] += (((long)m - s) << LARGE_SHIFT);
                buffer[t] += (((long)s - i) << LESS_SHIFT);
                data[k++] = buffer[t++];
            } else {
                long value = (buffer[s] & VAL_MASK);
                int s1 = s;
                while (s1 < m && (buffer[s1] & VAL_MASK) == value) {
                    data[k++] = buffer[s1++];
                }

                while (t < j && (buffer[t] & VAL_MASK) == value) {
                    buffer[t] += (((long) m - s1) << LARGE_SHIFT);
                    buffer[t] += (((long) s - i) << LESS_SHIFT);
                    data[k++] = buffer[t++];
                }

                s = s1;
            }

        }

        while (s < m) {
            data[k++] = buffer[s++];
        }
        while (t < j) {
            buffer[t] += (((long) m - i) << LESS_SHIFT);
            data[k++] = buffer[t++];
        }
    }

    public static void main(String[] args) {
        System.out.println(new InsertionSortCountBit().createSortedArray(new int[]{
                1,3,3,3,2,4,2,1,2
        }));
    }
}
