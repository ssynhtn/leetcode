package com.ssynhtn.contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindServersThatHandledMostNumberofRequests {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        long[] availableTime = new long[k];
        int[] servedRequests = new int[k];



        for (int i = 0; i < arrival.length; i++) {
            for (int j = 0; j < k; j++) {
                int index = (i + j) % k;
                if (availableTime[index] <= arrival[i]) {
                    servedRequests[index]++;
                    availableTime[index] = arrival[i] + load[i];
                    break;
                }
            }
        }

        System.out.println(Arrays.toString(servedRequests));
        int max = 0;
        List<Integer> maxServers = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (servedRequests[i] > max) {
                maxServers.clear();
                maxServers.add(i);
                max = servedRequests[i];
            } else if (servedRequests[i] == max) {
                maxServers.add(i);
            }
        }

        return maxServers;

    }

    public static void main(String[] args) {
//        4
//                [1,3,4,5,10,12]
//[11,9,3,1,9,12]
        System.out.println(new FindServersThatHandledMostNumberofRequests().busiestServers(4, new int[]{1,3,4,5,10,12 }, new int[]{11,9,3,1,9,12}));

    }
}
