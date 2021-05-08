package apps.mainpage.pages.profile.listener;

import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.logic.PanesController;
import apps.mainpage.pages.profile.event.ProfileEvent;
import apps.mainpage.pages.profile.view.AddTweetPane;
import apps.mainpage.pages.profile.view.AddTweetPaneFXML;
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
        int page;

        switch (((Button) eventObject.getSource()).getId())
        {
            case "tweetButton":
                AddTweetPane addTweetPane = new AddTweetPane();
                AddTweetPaneFXML addTweetPaneController = addTweetPane.getLoader().getController();
                addTweetPaneController.setListener(((ProfilePaneFXML) profilePane.getLoader().getController()).getListener());
                fxmlController.setMainPane(addTweetPane.getNewTweetPane());
                break;
            case "nextButton":
                page = eventObject.getPageEvent().getPage();
                fxmlController.setMainPane(PanesController.getPanesController().getProfilePane(page + 1).getProfilePane());
                break;
            case "previousButton":
                page = eventObject.getPageEvent().getPage();
                fxmlController.setMainPane(PanesController.getPanesController().getProfilePane(page - 1).getProfilePane());
                break;
            case "sendTweetButton":
                String tweetText = eventObject.getTweetEvent().getTweetText();
                String tweetPic = eventObject.getTweetEvent().getPicPath();
                new Tweet(MainPageAgent.getMainPageAgent().getUser(), tweetText, tweetPic);
                fxmlController.setMainPane(PanesController.getPanesController().getProfilePane(0).getProfilePane());
                break;
            case "cancelButton":
                fxmlController.setMainPane(PanesController.getPanesController().getProfilePane(0).getProfilePane());
                break;
        }
    }
}
