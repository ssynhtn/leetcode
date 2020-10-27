package com.ssynhtn.mock;

public class InformTime {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Integer[] totalTimes = new Integer[n];
        int maxTime = 0;
        for (int i = 0; i < n; i++) {
            maxTime = Math.max(maxTime, computeTime(i, manager, informTime, totalTimes));
        }

        return maxTime;
    }

    private int computeTime(int i, int[] manager, int[] informTime, Integer[] totalTimes) {
        if (totalTimes[i] != null) return totalTimes[i];
        if (manager[i] == -1) {
            totalTimes[i] = 0;
            return 0;
        }

        int m = manager[i];
        int mTime = computeTime(m, manager, informTime, totalTimes);
        totalTimes[i] = mTime + informTime[m];
        return totalTimes[i];
    }
}
