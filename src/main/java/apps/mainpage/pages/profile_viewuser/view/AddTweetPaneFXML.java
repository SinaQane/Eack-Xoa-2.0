package apps.mainpage.pages.profile_viewuser.view;

import apps.mainpage.pages.profile_viewuser.event.ProfileEvent;
import apps.mainpage.pages.profile_viewuser.event.TweetFormEvent;
import apps.mainpage.pages.profile_viewuser.listener.ProfileListener;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddTweetPaneFXML
{
    private ProfileListener listener;

    public TextField tweetTextField;
    public TextField picsPathTextField;
    public Button cancelButton;
    public Button sendTweetButton;

    public void setListener(ProfileListener listener)
    {
        this.listener = listener;
    }

    public void cancel()
    {
        listener.eventOccurred(new ProfileEvent(cancelButton));
    }

    public void tweet()
    {
        TweetFormEvent tweetEvent = new TweetFormEvent(tweetTextField.getText(), picsPathTextField.getText());
        listener.eventOccurred(new ProfileEvent(sendTweetButton, tweetEvent));
    }
}
