package apps.newtweet.listener;

import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.logic.PanesController;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import apps.newtweet.event.NewTweetEvent;
import db.TweetDB;
import javafx.scene.control.Button;
import models.Tweet;

public class NewTweetFrameListener
{
    private long id = -1;

    public void setId(long id)
    {
        this.id = id;
    }

    public void eventOccurred(NewTweetEvent eventObject)
    {
        MainPageFXML mainPageController = MainPage.getMainPage().getLoader().getController();

        if (((Button) eventObject.getSource()).getId().equals("sendTweetButton"))
        {
            String upperTweetId = eventObject.getTweetForm().getUpperTweet();
            String tweetText = eventObject.getTweetForm().getTweetText();
            String tweetPic = eventObject.getTweetForm().getPicPath();
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
        if (id != -1)
        {
            mainPageController.setMainPane(PanesController.getPanesController().getProfilePane(id, 0).getProfilePane());
        }
        else
        {
            mainPageController.setMainPane(PanesController.getPanesController().getProfilePane(MainPageAgent.getMainPageAgent().getUser().getId(), 0).getProfilePane());

        }
    }
}
