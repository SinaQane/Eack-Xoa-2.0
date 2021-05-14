package controller.components.tweet;

import db.TweetDB;
import db.UserDB;
import model.Tweet;
import model.User;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class TweetPaneLogic
{
    private static final Logger logger = LogManager.getLogger(TweetPaneLogic.class);

    private final User ourUser;
    private final User otherUser; // TODO direct messages
    private final Tweet tweet;

    public TweetPaneLogic(User ourUser, User otherUser, Tweet tweet)
    {
        this.ourUser = ourUser;
        this.otherUser = otherUser;
        this.tweet = tweet;
    }

    public void upvote()
    {
        if (ourUser.getProfile().getUpvotedTweets().contains(tweet.getId()))
        {
            ourUser.getProfile().removeFromUpvotedTweets(tweet);
            tweet.removeUpvote(ourUser);
            logger.debug(ourUser.getId() + " removed their upvote for tweet " + tweet.getId());
        }
        else
        {
            ourUser.getProfile().removeFromDownvotedTweets(tweet);
            ourUser.getProfile().addToUpvotedTweets(tweet);
            tweet.removeDownvote(ourUser);
            tweet.addUpvote(ourUser);
            logger.debug(ourUser.getId() + " upvoted tweet " + tweet.getId());
        }
        UserDB.getUserDB().save(ourUser);
        TweetDB.getTweetDB().save(tweet);
    }

    public void downvote()
    {
        if (ourUser.getProfile().getDownvotedTweets().contains(tweet.getId()))
        {
            ourUser.getProfile().removeFromDownvotedTweets(tweet);
            tweet.removeDownvote(ourUser);
            logger.debug(ourUser.getId() + " removed their downvote for tweet " + tweet.getId());
        }
        else
        {
            ourUser.getProfile().removeFromUpvotedTweets(tweet);
            ourUser.getProfile().addToDownvotedTweets(tweet);
            tweet.removeUpvote(ourUser);
            tweet.addDownvote(ourUser);
            logger.debug(ourUser.getId() + " downvoted tweet " + tweet.getId());
        }
        UserDB.getUserDB().save(ourUser);
        TweetDB.getTweetDB().save(tweet);
    }

    public void retweet()
    {
        if (ourUser.getProfile().getRetweetedTweets().contains(tweet.getId()))
        {
            ourUser.getProfile().removeFromRetweetedTweets(tweet);
            tweet.removeRetweet(ourUser);
            logger.debug(ourUser.getId() + " removed their retweet for tweet " + tweet.getId());
        }
        else
        {
            ourUser.getProfile().addToRetweetedTweets(tweet);
            tweet.addRetweet(ourUser);
            logger.debug(ourUser.getId() + " retweeted tweet " + tweet.getId());
        }
        UserDB.getUserDB().save(ourUser);
        TweetDB.getTweetDB().save(tweet);
    }

    public void save()
    {
        if (ourUser.getProfile().getSavedTweets().contains(tweet.getId()))
        {
            ourUser.getProfile().removeFromSavedTweets(tweet);
            logger.debug(ourUser.getId() + " removed tweet " + tweet.getId() + " from their saved tweets.");
        }
        else
        {
            ourUser.getProfile().addToSavedTweets(tweet);
            logger.debug(ourUser.getId() + " saved tweet " + tweet.getId());
        }
        UserDB.getUserDB().save(ourUser);
    }

    public void report()
    {
        if (!ourUser.getProfile().getReportedTweets().contains(tweet.getId()))
        {
            ourUser.getProfile().addToReportedTweets(tweet);
            tweet.addReport();
            logger.debug(ourUser.getId() + " reported tweet " + tweet.getId());

            if (tweet.getReports() > 9)
            {
                tweet.deleteTweet();
                logger.debug(tweet.getId() + " got deleted since it broke the report limit.");
            }

            UserDB.getUserDB().save(ourUser);
            TweetDB.getTweetDB().save(tweet);
        }
    }

}
