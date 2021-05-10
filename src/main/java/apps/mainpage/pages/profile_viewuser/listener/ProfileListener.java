package apps.mainpage.pages.profile_viewuser.listener;

import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.logic.PanesController;
import apps.mainpage.pages.profile_viewuser.event.ProfileEvent;
import apps.mainpage.pages.profile_viewuser.view.AddTweetPane;
import apps.mainpage.pages.profile_viewuser.view.AddTweetPaneFXML;
import apps.mainpage.pages.profile_viewuser.view.ProfilePane;
import apps.mainpage.pages.profile_viewuser.view.ProfilePaneFXML;
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

        long id = eventObject.getPageEvent().getUser().getId();
        int page = eventObject.getPageEvent().getPage();

        switch (((Button) eventObject.getSource()).getId())
        {
            case "tweetButton":
                AddTweetPane addTweetPane = new AddTweetPane();
                AddTweetPaneFXML addTweetPaneController = addTweetPane.getLoader().getController();
                addTweetPaneController.setListener(((ProfilePaneFXML) profilePane.getLoader().getController()).getProfileListener());
                fxmlController.setMainPane(addTweetPane.getNewTweetPane());
                break;
            case "nextButton":
                fxmlController.setMainPane(PanesController.getPanesController().getProfilePane(id, page + 1).getProfilePane());
                break;
            case "previousButton":
                fxmlController.setMainPane(PanesController.getPanesController().getProfilePane(id, page - 1).getProfilePane());
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
