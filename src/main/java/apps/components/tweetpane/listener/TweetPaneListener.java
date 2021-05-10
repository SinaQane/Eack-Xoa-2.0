package apps.components.tweetpane.listener;

import apps.components.imageframe.ImageFrame;
import apps.components.tweetpane.event.TweetPaneEvent;
import apps.components.tweetpane.logic.TweetPaneAgent;
import apps.components.tweetpane.view.TweetPane;
import apps.components.tweetpane.view.TweetPaneFXML;
import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.pages.profile_viewuser.listener.UserViewListener;
import apps.mainpage.pages.profile_viewuser.view.ProfilePane;
import apps.mainpage.pages.profile_viewuser.view.ProfilePaneFXML;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import db.TweetDB;
import db.UserDB;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import models.Tweet;
import models.User;

import java.io.File;
import java.net.MalformedURLException;

// A listener for TweetPane.fxml panes
public class TweetPaneListener
{
    TweetPane tweetPane;

    public TweetPaneListener(TweetPane tweetPane)
    {
        this.tweetPane = tweetPane;
    }

    public void eventOccurred(TweetPaneEvent eventObject)
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
                ProfilePane userPane = new ProfilePane(otherUser);
                ((ProfilePaneFXML) userPane.getLoader().getController()).setUserViewListener(new UserViewListener());
                userPane.refresh(0);
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
                    imagePath = "src/main/resources/404.png";
                }

                try
                {
                    new ImageFrame(new Image(new File(imagePath).toURI().toURL().toExternalForm()));
                } catch (MalformedURLException ignored) {}

                break;
            case "viewTweetButton":
            case "commentButton":
            case "sendButton":
                System.out.println(((Button) eventObject.getSource()).getId());
                break;
        }
    }
}
