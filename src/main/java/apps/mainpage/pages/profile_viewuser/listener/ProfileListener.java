package apps.mainpage.pages.profile_viewuser.listener;

import apps.mainpage.logic.PanesController;
import apps.newtweet.view.NewTweetFrame;
import apps.newtweet.view.NewTweetFrameFXML;
import apps.mainpage.pages.profile_viewuser.event.ProfileEvent;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.scene.control.Button;

public class ProfileListener
{
    public void eventOccurred(ProfileEvent eventObject)
    {
        MainPageFXML fxmlController = MainPage.getMainPage().getLoader().getController();

        long id = eventObject.getProfileForm().getUser().getId();
        int page = eventObject.getProfileForm().getPage();

        switch (((Button) eventObject.getSource()).getId())
        {
            case "tweetButton":
                NewTweetFrame tweetFrame = new NewTweetFrame("");
                ((NewTweetFrameFXML) tweetFrame.getLoader().getController()).setUpperTweet("");
                ((NewTweetFrameFXML) tweetFrame.getLoader().getController()).setId(id);
                break;
            case "nextButton":
                fxmlController.setMainPane(PanesController.getPanesController().getProfilePane(id, page + 1).getProfilePane());
                break;
            case "previousButton":
                fxmlController.setMainPane(PanesController.getPanesController().getProfilePane(id, page - 1).getProfilePane());
                break;
        }
    }
}
