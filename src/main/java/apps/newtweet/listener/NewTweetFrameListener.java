package apps.newtweet.listener;

import apps.mainpage.logic.MainPageAgent;
import apps.newtweet.event.NewTweetEvent;
import db.TweetDB;
import javafx.scene.control.Button;
import models.Tweet;

public class NewTweetFrameListener
{
    public void eventOccurred(NewTweetEvent eventObject)
    {
        if (((Button) eventObject.getSource()).getId().equals("sendTweetButton"))
        {
            String upperTweetId = eventObject.getTweetFormEvent().getUpperTweet();
            String tweetText = eventObject.getTweetFormEvent().getTweetText();
            String tweetPic = eventObject.getTweetFormEvent().getPicPath();
            Tweet tweet = new Tweet(MainPageAgent.getMainPageAgent().getUser(), tweetText, tweetPic);

            if (!upperTweetId.equals(""))
            {
                Tweet upperTweet = TweetDB.getTweetDB().get(upperTweetId);
                tweet.setUpperTweetId(upperTweetId);
                upperTweet.addComment(tweet);
                TweetDB.getTweetDB().save(tweet);
                TweetDB.getTweetDB().save(upperTweet);
            }
        }
    }
}
