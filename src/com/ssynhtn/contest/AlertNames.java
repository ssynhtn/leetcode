package com.ssynhtn.contest;

import java.util.*;

public class AlertNames {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        List<String> res = new ArrayList<>();
        Map<String, List<String>> nameToTimes = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            String name = keyName[i];
            List<String> times = nameToTimes.computeIfAbsent(name, k -> new ArrayList<>());
            times.add(keyTime[i]);
        }

        for (String name : nameToTimes.keySet()) {
            List<String> times = nameToTimes.get(name);
            if (times == null || times.size() < 3) continue;

            Collections.sort(times);

            int limit = times.size() - 2;
            for (int i = 0; i < limit; i++) {
                String first = times.get(i);
                String second = times.get(i + 2);

                String h1 = first.substring(0, 2);
                String h2 = second.substring(0, 2);

                int hour1 = toNum(h1);
                int hour2 = toNum(h2);

                if (hour1 == hour2) {
                    res.add(name);
                    break;
                }

                if (hour2 - hour1 == 1) {
                    String m1 = first.substring(3, 5);
                    String m2 = second.substring(3, 5);
                    int minute1 = toNum(m1);
                    int minute2 = toNum(m2);
                    if (minute2 <= minute1) {
                        res.add(name);
                        break;
                    }

                }
            }

        }

        Collections.sort(res);
        return res;
    }

    private int toNum(String h1) {
        int hour1;
        if (h1.startsWith("0")) {
            hour1 = h1.charAt(1) - '0';
        } else {
            hour1 = Integer.parseInt(h1);
        }

        return hour1;
    }
}
