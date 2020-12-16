package com.ssynhtn.hard;

import com.ssynhtn.common.TrieNode;

import java.math.BigInteger;
import java.util.*;

class WordBreakII {

    BigInteger foo(int n) {
        if (n < 0) return BigInteger.ZERO;
        if (n <= 1) return BigInteger.ONE;

        BigInteger[] buff = new BigInteger[11];
        buff[0] = BigInteger.ONE;
        buff[1] = BigInteger.ONE;
        for (int j = 2; j < buff.length; j++) {
            buff[j] = BigInteger.ZERO;
        }

        int index = 1;

        for (int i = 2; i <= n; i++) {
            int next = index + 1;
            if (next == buff.length) {
                next = 0;
            }

            buff[next] = buff[index].multiply(BigInteger.valueOf(2)).subtract(buff[next]);
            index = next;
        }

        return buff[index];
    }

    Map<Integer, List<List<String>>> cache = new HashMap<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        boolean[] occ = new boolean[26];
        for (String w : wordDict) {
            for (char ch : w.toCharArray()) {
                occ[ch - 'a'] = true;
            }
        }

        char[] chs = s.toCharArray();
        int n = chs.length;
        for (char ch : chs) {
            if (!occ[ch - 'a']) {
                return Collections.emptyList();
            }
        }
        TrieNode root = TrieNode.buildTree(wordDict);


        List<String> collect = new ArrayList<>();

        List<List<String>> res = collect(0, n, chs, root);
        for (List<String> list : res) {
            collect.add(String.join(" ", list));
        }
        return collect;

    }

    private List<List<String>> collect(int i, int n, char[] chs, TrieNode root) {
        if (cache.containsKey(i)) {
            return cache.get(i);
        }
        if (i == n) {
            List<List<String>> res = Collections.singletonList(Collections.emptyList());
            cache.put(i, res);
            return res;
        }


        List<List<String>> res = new ArrayList<>();
        int j = i;
        TrieNode node = root;
        while (node != null) {
            if (node.isWord) {
                List<List<String>> nexts = collect(j, n , chs, root);
                for (List<String> next : nexts) {
                    List<String> curr = new ArrayList<>();
                    curr.add(node.word);
                    curr.addAll(next);
                    res.add(curr);
                }
            }

            if (j < n) {
                node = node.children[chs[j] - 'a'];
                j++;
            } else {
                break;
            }
        }

        cache.put(i, res);
        return res;
    }


    public List<String> wordBreakHash(String s, List<String> wordDict) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        Set<String> dict = new HashSet<>(wordDict);

        int maxLen = 0;
        for (String w : dict) {
            maxLen = Math.max(maxLen, w.length());
        }

        List<String> collect = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        collect(collect, sb, 0, n, chs, dict, maxLen);
        return collect;
    }

    private void collect(List<String> collect, StringBuilder sb, int i, int n, char[] chs, Set<String> dict, final int maxLen) {
        if (i == n) {
            collect.add(sb.toString());
            return;
        }

        int limit = Math.min(n-i, maxLen);
        for (int len = 1; len <= limit; len++) {
            String word = new String(chs, i, len);
            if (dict.contains(word)) {
                int start = sb.length();
                if (start == 0) {
                    sb.append(word);
                } else {
                    sb.append(" ").append(word);
                }

                collect(collect, sb, i + len, n, chs, dict, maxLen);
                sb.delete(start, sb.length());
            }
        }


    }

    public static void main(String[] args) {
//        for (int n = 0; n <= 20; n++) {
//            System.out.println("n " + n + ", out " + new WordBreakII().foo(n));
//        }
        String input = "pineapplepenapple";
//        System.out.println(new WordBreakII().foo(151));
        List<String> dict = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");

        List<String> res = new WordBreakII().wordBreak(input, dict);
        System.out.println(res);

//        List<String> res = new WordBreakII().wordBreak(input,
//                Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"));
//        System.out.println("size " + res.size());
//        System.out.println(res);

//        List<String> resHash = new WordBreakII().wordBreakHash(input, dict);
//        System.out.println("hashSize " + resHash.size());
//        System.out.println(resHash);
    }
}