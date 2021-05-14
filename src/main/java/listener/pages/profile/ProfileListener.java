package listener.pages.profile;

import controller.mainpage.PanesController;
import view.frames.newtweet.NewTweetFrame;
import view.frames.newtweet.NewTweetFrameFXML;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.scene.control.Button;

public class ProfileListener
{
    public void eventOccurred(ProfileEvent eventObject)
    {
        MainPageFXML mainPageFXML = MainPage.getMainPage().getLoader().getController();

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
                mainPageFXML.setMainPane(PanesController.getPanesController().getProfilePane(id, page + 1).getPane());
                break;
            case "previousButton":
                mainPageFXML.setMainPane(PanesController.getPanesController().getProfilePane(id, page - 1).getPane());
                break;
        }
    }
}
