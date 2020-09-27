package com.ssynhtn.medium;

import javax.swing.event.TreeWillExpandListener;
import java.awt.peer.LightweightPeer;
import java.util.*;

public class Twitter {

    static int timeStamp;

    class UserTweet {
        int id;
        UserTweet next;
        int timeStamp;
    }

    Map<Integer, UserTweet> userTweets = new HashMap<>();
    Map<Integer, Set<Integer>> userFollows = new HashMap<>();


    /** Initialize your data structure here. */
    public Twitter() {

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        UserTweet head = userTweets.get(userId);
        UserTweet tweet = new UserTweet();
        tweet.id = tweetId;
        tweet.next = head;
        tweet.timeStamp = timeStamp++;
        userTweets.put(userId, tweet);


    }

    // Priority Queue of heads
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> targets = userFollows.get(userId);
        UserTweet self = userTweets.get(userId);
        if ((targets == null || targets.isEmpty())) {
            while (self != null && result.size() < 10) {
                result.add(self.id);
                self = self.next;
            }
            return result;
        }

        PriorityQueue<UserTweet> pq = new PriorityQueue<>(new Comparator<UserTweet>() {
            @Override
            public int compare(UserTweet o1, UserTweet o2) {
                return o2.timeStamp - o1.timeStamp;
            }
        });

        for (Integer tId : targets) {
            UserTweet head = userTweets.get(tId);
            if (head != null) {
                pq.add(head);
            }
        }
        if (self != null) {
            pq.add(self);
        }

        while (result.size() < 10 && !pq.isEmpty()) {
            UserTweet head = pq.poll();
            result.add(head.id);
            if (head.next != null) {
                pq.add(head.next);
            }
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        Set<Integer> targets = userFollows.computeIfAbsent(followerId, k -> new HashSet<>());
        targets.add(followeeId);


    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> targets = userFollows.get(followerId);
        if (targets == null) return;
        targets.remove(followeeId);
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.postTweet(1, 3);
        System.out.println(twitter.getNewsFeed(1));
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */