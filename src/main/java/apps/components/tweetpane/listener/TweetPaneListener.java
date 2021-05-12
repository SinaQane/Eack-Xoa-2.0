package apps.components.tweetpane.listener;

import apps.imageframe.ImageFrame;
import apps.components.tweetpane.logic.TweetPaneAgent;
import apps.components.tweetpane.view.TweetPane;
import apps.components.tweetpane.view.TweetPaneFXML;
import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.logic.PanesController;
import apps.newtweet.view.NewTweetFrame;
import apps.newtweet.view.NewTweetFrameFXML;
import apps.mainpage.pages.profile_viewuser.view.ProfilePane;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import db.TweetDB;
import db.UserDB;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import models.Tweet;
import models.User;
import utils.Config;

import java.io.File;
import java.net.MalformedURLException;
import java.util.EventObject;

public class TweetPaneListener
{
    private static final String NOT_FOUND = Config.getConfig("paths").getProperty(String.class, "notFound");

    private final TweetPane tweetPane;

    public TweetPaneListener(TweetPane tweetPane)
    {
        this.tweetPane = tweetPane;
    }

    public void eventOccurred(EventObject eventObject)
    {
        User ourUser = MainPageAgent.getMainPageAgent().getUser();
        User otherUser = UserDB.getUserDB().get(((TweetPaneFXML) tweetPane.getLoader().getController()).getOwnerId());
        Tweet tweet = TweetDB.getTweetDB().get(((TweetPaneFXML) tweetPane.getLoader().getController()).getTweetId());

        TweetPaneAgent logicalAgent = new TweetPaneAgent(ourUser, otherUser, tweet);

        switch (((Button) eventObject.getSource()).getId())
        {
            case "upvoteButton":
                logicalAgent.upvote();
                ((TweetPaneFXML) tweetPane.getLoader().getController()).refreshButtons();
                break;
            case "downvoteButton":
                logicalAgent.downvote();
                ((TweetPaneFXML) tweetPane.getLoader().getController()).refreshButtons();
                break;
            case "retweetButton":
                logicalAgent.retweet();
                ((TweetPaneFXML) tweetPane.getLoader().getController()).refreshButtons();
                break;
            case "saveButton":
                logicalAgent.save();
                ((TweetPaneFXML) tweetPane.getLoader().getController()).refreshButtons();
                break;
            case "reportButton":
                logicalAgent.report();
                ((TweetPaneFXML) tweetPane.getLoader().getController()).refreshButtons();
                break;
            case "viewUserButton":
                ProfilePane userPane = PanesController.getPanesController().getProfilePane(otherUser.getId(), 0);
                ((MainPageFXML) MainPage.getMainPage().getLoader().getController()).setMainPane(userPane.getProfilePane());
                break;
            case "viewImageButton":
                boolean correctPath;

                if (tweet.getPicturePath().equals(""))
                {
                    correctPath = false;
                }
                else
                {
                    File file = new File(tweet.getPicturePath());
                    correctPath = file.exists();
                }

                String imagePath;
                if (correctPath)
                {
                    imagePath = tweet.getPicturePath();
                }
                else
                {
                    imagePath = NOT_FOUND;
                }

                try
                {
                    new ImageFrame(new Image(new File(imagePath).toURI().toURL().toExternalForm()));
                } catch (MalformedURLException ignored) {}
                break;
            case "viewTweetButton":
                Pane viewTweet = PanesController.getPanesController().getTweetsListPane(tweet.getId(), 0).getListPane();
                ((MainPageFXML) MainPage.getMainPage().getLoader().getController()).setMainPane(viewTweet);
                break;
            case "viewUpperTweetButton":
                Pane viewUpper = PanesController.getPanesController().getTweetsListPane(tweet.getUpperTweetId(), 0).getListPane();
                ((MainPageFXML) MainPage.getMainPage().getLoader().getController()).setMainPane(viewUpper);
                break;
            case "commentButton":
                NewTweetFrame tweetFrame = new NewTweetFrame(tweet.getId());
                ((NewTweetFrameFXML) tweetFrame.getLoader().getController()).setUpperTweet(tweet.getId());
                break;
            case "sendButton": // TODO direct messages
                System.out.println(((Button) eventObject.getSource()).getId());
                break;
        }
    }
}
