package models;

import java.util.*;

import db.TweetDB;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import utils.HashMapUtil;

public class Profile
{
    static private final Logger logger = LogManager.getLogger(Profile.class); // TODO use logger

    private final long ownerId; // TODO find a way to save user after changes in profile
    private long lastTweetId = 0;

    // Interactions with other users
    private final List<Long> followers = new LinkedList<>();
    private final List<Long> followings = new LinkedList<>();
    private final List<Long> blocked = new LinkedList<>();
    private final List<Long> muted = new LinkedList<>();
    private final List<Long> reported = new LinkedList<>();
    private final List<Long> requests = new LinkedList<>();
    private final List<Long> pending = new LinkedList<>();

    // Tweets
    private final List<String> userTweets = new LinkedList<>();
    private final List<String> retweetedTweets = new LinkedList<>();
    private final List<String> upvotedTweets = new LinkedList<>();
    private final List<String> downvotedTweets = new LinkedList<>();
    private final List<String> reportedTweets = new LinkedList<>();

    // Privacy
    private boolean privateState; // "true" if the page is private, "false" if if it's public.
    private boolean infoState; // For Email, Phone number and Birthdate. "true" for public and "false" for private.
    private int lastSeenState; // "0" for no one, "1" for followings only and "2" for everyone.

    Profile(long ownerId)
    {
        this.ownerId = ownerId;
        this.privateState = false;
        this.infoState = false;
        this.lastSeenState = 1;
    }

    public long getLastTweetId()
    {
        return this.lastTweetId;
    }

    public void setLastTweetId(long lastTweetId)
    {
        this.lastTweetId = lastTweetId;
    }

    public boolean isPrivate()
    {
        return this.privateState;
    }

    public void setPrivate(boolean privateState)
    {
        this.privateState = privateState;
    }

    public boolean getInfoState()
    {
        return this.infoState;
    }

    public void setInfoState(boolean infoState)
    {
        this.infoState = infoState;
    }

    public int getLastSeenState()
    {
        return this.lastSeenState;
    }

    public void setLastSeenState(int lastSeenState)
    {
        this.lastSeenState = lastSeenState;
    }

    public void addToFollowers(User user)
    {
        this.followers.add(user.getId());
    }

    public void removeFromFollowers(User user)
    {
        this.followers.remove(user.getId());
    }

    public List<Long> getFollowers()
    {
        return this.followers;
    }

    public void addToFollowings(User user)
    {
        this.followings.add(user.getId());
    }

    public void removeFromFollowings(User user)
    {
        this.followings.remove(user.getId());
    }

    public List<Long> getFollowings()
    {
        return this.followings;
    }

    public void addToBlocked(User user)
    {
        this.blocked.add(user.getId());
    }

    public void removeFromBlocked(User user)
    {
        this.blocked.remove(user.getId());
    }

    public List<Long> getBlocked()
    {
        return this.blocked;
    }

    public void addToMuted(User user)
    {
        this.muted.add(user.getId());
    }

    public void removeFromMuted(User user)
    {
        this.muted.remove(user.getId());
    }

    public List<Long> getMuted()
    {
        return this.muted;
    }

    public void addToReported(User user)
    {
        this.reported.add(user.getId());
    }

    public void removeFromReported(User user)
    {
        this.reported.remove(user.getId());
    }

    public List<Long> getReported()
    {
        return this.reported;
    }

    public void addToRequests(User user)
    {
        this.requests.add(user.getId());
    }

    public void removeFromRequests(User user)
    {
        this.requests.remove(user.getId());
    }

    public List<Long> getRequests()
    {
        return this.requests;
    }

    public void addToPending(User user)
    {
        this.pending.add(user.getId());
    }

    public void removeFromPending(User user)
    {
        this.pending.remove(user.getId());
    }

    public List<Long> getPending()
    {
        return this.pending;
    }

    public void addToUserTweets(Tweet tweet)
    {
        this.userTweets.add(tweet.getId());
    }

    public void removeFromUserTweets(Tweet tweet)
    {
        this.userTweets.remove(tweet.getId());
    }

    public List<String> getUserTweets()
    {
        return this.userTweets;
    }

    public void addToRetweetedTweets(Tweet tweet)
    {
        this.retweetedTweets.add(tweet.getId());
    }

    public void removeFromRetweetedTweets(Tweet tweet)
    {
        this.retweetedTweets.remove(tweet.getId());
    }

    public List<String> getRetweetedTweets()
    {
        return this.retweetedTweets;
    }

    public void addToUpvotedTweets(Tweet tweet)
    {
        this.upvotedTweets.add(tweet.getId());
    }

    public void removeFromUpvotedTweets(Tweet tweet)
    {
        this.upvotedTweets.remove(tweet.getId());
    }

    public List<String> getUpvotedTweets()
    {
        return this.upvotedTweets;
    }

    public void addToDownvotedTweets(Tweet tweet)
    {
        this.downvotedTweets.add(tweet.getId());
    }

    public void removeFromDownvotedTweets(Tweet tweet)
    {
        this.downvotedTweets.remove(tweet.getId());
    }

    public List<String> getDownvotedTweets()
    {
        return this.downvotedTweets;
    }

    public void addToReportedTweets(Tweet tweet)
    {
        this.reportedTweets.add(tweet.getId());
    }

    public void removeFromReportedTweets(Tweet tweet)
    {
        this.reportedTweets.remove(tweet.getId());
    }

    public List<String> getReportedTweets()
    {
        return this.reportedTweets;
    }

    /*
    A HashMap that links every tweet to 2 variables:
    1. A bit that shows that is this tweet a retweet ("0") or the user's tweet ("1").
    2. A long that shows the time that this tweet was tweeted, in milliseconds.
    */
    public List<String[]> getHomePageTweets()
    {
        HashMap<String[], Long> homePageTweets = new HashMap<>();

        for (String userTweet : userTweets)
        {
            Tweet tweet = TweetDB.getTweetDB().get(userTweet);
            homePageTweets.put(new String[]{userTweet, "1"}, tweet.getTweetDate().getTime());
        }

        for (String retweetedTweet : retweetedTweets)
        {
            Tweet tweet = TweetDB.getTweetDB().get(retweetedTweet);
            homePageTweets.put(new String[]{retweetedTweet, "0"}, tweet.getTweetDate().getTime());
        }

        List<String[]> result = new LinkedList<>();

        for (Map.Entry<String[], Long> e : HashMapUtil.sortByValue(homePageTweets).entrySet())
        {
            result.add(0, e.getKey());
        }

        return result;
    }
}