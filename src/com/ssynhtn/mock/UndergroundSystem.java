package com.ssynhtn.mock;

import java.util.HashMap;
import java.util.Map;

public class UndergroundSystem {
    
    /*
    key: startStation-endStation
    n travels from start to end
    total time from start to end
    
    when checking out, needs to know which start station
    map: id -> <start station, time>
    
    
    */
    
    static class CheckIn {
        String start;
        int t;
        CheckIn(String start, int t) {
            this.start = start;
            this.t = t;
        }
    }
    
    Map<Integer, CheckIn> checkIns = new HashMap<>();
    Map<String, Integer> travelCounts = new HashMap<>();
    Map<String, Long> travelTime = new HashMap<>();

    public UndergroundSystem() {
        
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new CheckIn(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        CheckIn cin = checkIns.remove(id);
        String key = cin.start + "-" + stationName;
        travelCounts.merge(key, 1, Integer::sum);
        travelTime.merge(key, (long) (t - cin.t), Long::sum);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "-" + endStation;
        Integer n = travelCounts.get(key);
        if (n == null) return 0;
        Long t = travelTime.get(key);
        return t * 1.0 / n;
    }
}