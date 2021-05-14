package model;

import java.util.*;

import db.TweetDB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.HashMapUtil;

public class Profile
{
    private static final Logger logger = LogManager.getLogger(Profile.class);

    private final long ownerId;
    private long lastTweetId = 0;

    private String picturePath = "";

    // Interactions with other users
    private final List<Long> followers = new LinkedList<>();
    private final List<Long> followings = new LinkedList<>();
    private final List<Long> blocked = new LinkedList<>();
    private final List<Long> muted = new LinkedList<>();
    private final List<Long> requests = new LinkedList<>();
    private final List<Long> pending = new LinkedList<>();

    // Tweets
    private final List<String> userTweets = new LinkedList<>();
    private final List<String> retweetedTweets = new LinkedList<>();
    private final List<String> upvotedTweets = new LinkedList<>();
    private final List<String> downvotedTweets = new LinkedList<>();
    private final List<String> reportedTweets = new LinkedList<>();
    private final List<String> savedTweets = new LinkedList<>();

    // Notifications
    private final List<Notification> notifications = new LinkedList<>();

    // Groups
    private final List<Group> groups = new LinkedList<>();

    // Chats
    private final List<Long> chatIds = new LinkedList<>();

    // Privacy
    private boolean privateState; // "true" if the page is private, "false" if if it's public.
    private boolean infoState; // For Email, Phone number and Birthdate. "true" for public and "false" for private.
    private int lastSeenState; // "0" for no one, "1" for followings only and "2" for everyone.

    // Last seen date
    @SuppressWarnings("unused")
    private Date lastSeen;

    Profile(long ownerId)
    {
        this.ownerId = ownerId;
        this.privateState = false;
        this.infoState = false;
        this.lastSeenState = 1;
        this.lastSeen = new Date();
    }

    public long getLastTweetId()
    {
        return this.lastTweetId;
    }

    public void setLastTweetId(long lastTweetId)
    {
        this.lastTweetId = lastTweetId;
    }

    public String getPicturePath()
    {
        return this.picturePath;
    }

    public void setPicturePath(String picturePath)
    {
        if (this.picturePath != null)
        {
            if (this.picturePath.equals(picturePath))
            {
                return;
            }
        }
        this.picturePath = picturePath;
        logger.warn(this.ownerId + "'s profile picture was changed.");
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

    public List<String> getReportedTweets()
    {
        return this.reportedTweets;
    }

    public void addToSavedTweets(Tweet tweet)
    {
        this.savedTweets.add(tweet.getId());
    }

    public void removeFromSavedTweets(Tweet tweet)
    {
        this.savedTweets.remove(tweet.getId());
    }

    public List<String> getSavedTweets()
    {
        return this.savedTweets;
    }

    public void addToNotifications(Notification notification)
    {
        this.notifications.add(notification);
    }

    public List<Notification> getNotifications()
    {
        return this.notifications;
    }

    public void addToChats(Chat chat)
    {
        this.chatIds.add(chat.getId());
    }

    public List<Long> getChats()
    {
        return this.chatIds;
    }

    public List<Group> getGroups()
    {
        return this.groups;
    }

    public Group getGroup(String groupName)
    {
        for (Group group : groups)
        {
            if (group.getTitle().equals(groupName))
            {
                return group;
            }
        }

        return null;
    }

    public void addToGroups(Group group)
    {
        int index = -1;
        for (int i = 0; i < groups.size(); i++)
        {
            if (groups.get(i).getTitle().equals(group.getTitle()))
            {
                index = i;
            }
        }
        if(index != -1)
        {
            groups.remove(index);
        }
        groups.add(group);
    }

    public void setLastSeen(Date lastSeen)
    {
        this.lastSeen = lastSeen;
    }

    /*
                A HashMap that links every tweet to 2 variables:
                1. A bit that shows that is this tweet a retweet ("1") or the user's tweet ("0").
                2. A long that shows the time that this tweet was tweeted, in milliseconds.
                */
    public List<String[]> getHomePageTweets()
    {
        HashMap<String[], Long> homePageTweets = new HashMap<>();

        for (String userTweet : userTweets)
        {
            Tweet tweet = TweetDB.getTweetDB().get(userTweet);
            homePageTweets.put(new String[]{userTweet, "0"}, tweet.getTweetDate().getTime());
        }

        for (String retweetedTweet : retweetedTweets)
        {
            Tweet tweet = TweetDB.getTweetDB().get(retweetedTweet);
            homePageTweets.put(new String[]{retweetedTweet, "1"}, tweet.getTweetDate().getTime());
        }

        List<String[]> result = new LinkedList<>();

        for (Map.Entry<String[], Long> e : HashMapUtil.sortByValue(homePageTweets).entrySet())
        {
            result.add(0, e.getKey());
        }

        return result;
    }
}