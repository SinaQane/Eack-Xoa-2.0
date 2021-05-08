package models;

import db.TweetDB;
import db.UserDB;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Tweet
{
    static private final Logger logger = LogManager.getLogger(Tweet.class); // TODO use logger

    // Tweet info

    /*
    Tweet's ID will be in the form "num1-num2" where "num1" is its owner's ID,
    and "num2" is its number in owner's tweets' arrangement.
    */
    private final String id;
    private final long owner;
    private final String text;
    private final String picturePath;
    private final Date tweetDate;
    private boolean visible = true;

    /*
    If upperTweetId is equal to "", this tweet is a main tweet.
    If it's not, It's a comment and upperTweetId is its upperTweet's id.
    */
    private String upperTweetId = "";
    private final List<String> comments = new LinkedList<>();

    // Interactions of users with this tweet
    private final List<Long> upvotes = new ArrayList<>();
    private final List<Long> downvotes = new ArrayList<>();
    private final List<Long> retweets = new ArrayList<>();
    private int reports = 0;

    public Tweet(User owner, String text, String picturePath)
    {
        owner.getProfile().setLastTweetId(owner.getProfile().getLastTweetId() + 1);
        this.id = owner.getId() + "-" + owner.getProfile().getLastTweetId();
        this.owner = owner.getId();
        this.text = text;
        this.picturePath = picturePath;
        this.tweetDate = new Date();
        owner.getProfile().getUserTweets().add(this.id);
        TweetDB.getTweetDB().save(this);
        UserDB.getUserDB().save(owner);
    }

    public String getId()
    {
        return this.id;
    }

    public long getOwner()
    {
        return this.owner;
    }

    public String getText()
    {
        return this.text;
    }

    public String getPicturePath()
    {
        return this.picturePath;
    }

    public Date getTweetDate()
    {
        return this.tweetDate;
    }

    public boolean isVisible()
    {
        return this.visible;
    }

    public void deleteTweet()
    {
        this.visible = false;
    }

    public void setUpperTweet(Tweet upperTweet)
    {
        this.upperTweetId = upperTweet.getId();
    }

    public Tweet getUpperTweet()
    {
        return TweetDB.getTweetDB().get(this.upperTweetId);
    }

    public void addComment(Tweet comment)
    {
        this.comments.add(comment.getId());
    }

    public void removeComment(Tweet comment)
    {
        this.comments.remove(comment.getId());
    }

    public List<String> getComments()
    {
        return this.comments;
    }

    public void addUpvote(User user)
    {
        this.upvotes.add(user.getId());
    }

    public void removeUpvote(User user)
    {
        this.upvotes.remove(user.getId());
    }

    public List<Long> getUpvotes()
    {
        return this.upvotes;
    }

    public void addDownvote(User user)
    {
        this.downvotes.add(user.getId());
    }

    public void removeDownvote(User user)
    {
        this.downvotes.remove(user.getId());
    }

    public List<Long> getDownvotes()
    {
        return this.downvotes;
    }

    public void addRetweets(User user)
    {
        this.retweets.add(user.getId());
    }

    public void removeRetweets(User user)
    {
        this.retweets.remove(user.getId());
    }

    public List<Long> getRetweets()
    {
        return this.retweets;
    }

    public void reported()
    {
        this.reports++;
        // TODO check the number of reports...
    }

    public int getReports()
    {
        return this.reports;
    }
}
