package apps.mainpage.pages.profile.listener;

import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.logic.PanesController;
import apps.mainpage.pages.profile.event.ProfileEvent;
import apps.mainpage.pages.profile.logic.TweetsAgent;
import apps.mainpage.pages.profile.view.NewTweetPane;
import apps.mainpage.pages.profile.view.NewTweetPaneFXML;
import apps.mainpage.pages.profile.view.ProfilePane;
import apps.mainpage.pages.profile.view.ProfilePaneFXML;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.scene.control.Button;
import models.Tweet;

public class ProfileListener
{
    private final ProfilePane profilePane;

    public ProfileListener(ProfilePane profilePane)
    {
        this.profilePane = profilePane;
    }

    public void eventOccurred(ProfileEvent eventObject)
    {
        MainPageFXML fxmlController = MainPage.getMainPage().getLoader().getController();

        if (((Button)eventObject.getSource()).getId().equals("tweetButton"))
        {
            NewTweetPane newTweetPane = new NewTweetPane();
            NewTweetPaneFXML newTweetPaneController = newTweetPane.getLoader().getController();
            newTweetPaneController.setListener(((ProfilePaneFXML)profilePane.getLoader().getController()).getListener());
            fxmlController.setMainPane(newTweetPane.getNewTweetPane());
        }
        if (((Button)eventObject.getSource()).getId().equals("cancelButton"))
        {
            fxmlController.setMainPane(PanesController.getPanesController().getProfilePane(0).getProfilePane());
        }
        if (((Button)eventObject.getSource()).getId().equals("sendTweetButton"))
        {
            String tweetText = eventObject.getTweetEvent().getTweetText();
            String tweetPic = eventObject.getTweetEvent().getPicPath();
            new Tweet(MainPageAgent.getMainPageAgent().getUser(), tweetText, tweetPic);
            fxmlController.setMainPane(PanesController.getPanesController().getProfilePane(0).getProfilePane());
        }
    }
}
