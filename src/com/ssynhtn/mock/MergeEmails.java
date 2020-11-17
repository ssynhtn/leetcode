package com.ssynhtn.mock;

import java.util.*;

public class MergeEmails {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<List<String>>> accByName = new HashMap<>();
        
        for (List<String> acc : accounts) {
            String name = acc.get(0);
            List<List<String>> list = accByName.get(name);
            if (list == null) {
                list = new ArrayList<>();
                accByName.put(name, list);
            }
            list.add(acc);
        }
        
        List<List<String>> res = new ArrayList<>();
        for (String name : accByName.keySet()) {
            List<List<String>> accs = accByName.get(name);
            addToRes(res, accs);
        }
        
        return res;
        
    }
    
    void addToRes(List<List<String>> res, List<List<String>> accs) {
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> rank = new HashMap<>();
        
        String userName = accs.get(0).get(0);
        
        for (List<String> acc : accs) {
            for (int i = 1; i < acc.size(); i++) {
                String mail = acc.get(i);
                parent.put(mail, mail);
                rank.put(mail, 0);
            }
        }
        
        for (List<String> acc : accs) {
            for (int i = 1; i < acc.size() - 1; i++) {
                String a = acc.get(i);
                String b = acc.get(i+1);
                
                String ap = findParent(a, parent);
                String bp = findParent(b, parent);
                int r1 = rank.get(ap);
                int r2 = rank.get(bp);
                
                if (r1 < r2) {
                    parent.put(ap, bp);
                } else if (r1 > r2) {
                    parent.put(bp, ap);
                } else {
                    parent.put(ap, bp);
                    rank.put(bp, r2 + 1);
                }
                
                
            }
        }
        
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : parent.keySet()) {
            String p = findParent(s, parent);
            List<String> list = groups.get(p);
            if (list == null) {
                list = new ArrayList<>();
                groups.put(p, list);
            }
            list.add(s);
        }
        for (String p : groups.keySet()) {
            List<String> list = groups.get(p);
            Collections.sort(list);
            list.add(0, userName);
            res.add(list);
        }
        
    }
    

    String findParent(String a, Map<String, String> parent) {
        String p = parent.get(a);
        if (p.equals(a)) return p;
        String root = findParent(p, parent);
        parent.put(a, root);
        return root;
    }
}