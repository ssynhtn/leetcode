package com.ssynhtn.hard;

// nlogn
class CountRangeSumMerge {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i+1] = sums[i] + nums[i];
        }

        long[] buffer = new long[n + 1];
        return mergeSort(sums, buffer, 0, n, lower, upper);

    }

    // 对sum[left:right]进行sort， 同时计数范围内sums[j] - sums[i](i < j) in [lower, upper]的对
    private int mergeSort(long[] sums, long[] buffer, int left, int right, int lower, int upper) {
        if (left == right) return 0;
        int mid = left + (right - left) / 2;
        int count = 0;
        count += mergeSort(sums, buffer, left, mid, lower, upper);
        count += mergeSort(sums, buffer, mid + 1, right, lower, upper);

        int li = mid + 1;
        int ri = mid + 1;
        for (int i = left; i <= mid; i++) {
            long cl = sums[i] + lower;
            long cu = sums[i] + upper;

            while (li <= right && sums[li] < cl) {
                li++;
            }

            // li first index that >= cl
            while (ri <= right && sums[ri] <= cu) {
                ri++;
            }
            // ri first > cu

            count += ri - li;
        }

        // merge
        System.arraycopy(sums, left, buffer, left, mid - left + 1);
        int k = left;
        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (buffer[i] < sums[j]) {
                sums[k++] = buffer[i++];
            } else {
                sums[k++] = sums[j++];
            }
        }

        while (i <= mid) {
            sums[k++] = buffer[i++];
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new CountRangeSumMerge().countRangeSum(new int[]{-3,1,2,-2,2,-1}, -3, -1));
    }
}