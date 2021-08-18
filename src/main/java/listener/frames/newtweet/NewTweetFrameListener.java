package listener.frames.newtweet;

import controller.mainpage.MainPageController;
import controller.mainpage.PanesController;
import db.UserDB;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import db.TweetDB;
import javafx.scene.control.Button;
import model.Tweet;

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
            Tweet tweet = new Tweet(UserDB.getUserDB().get(MainPageController.getMainPageController().getUser().getId()), tweetText, tweetPic);

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
            mainPageController.setMainPane(PanesController.getPanesController().getProfilePane(id, 0).getPane());
        }
        else
        {
            mainPageController.setMainPane(PanesController.getPanesController().getProfilePane(MainPageController.getMainPageController().getUser().getId(), 0).getPane());

        }
    }
}
