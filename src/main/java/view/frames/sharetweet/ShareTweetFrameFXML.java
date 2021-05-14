package view.frames.sharetweet;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import listener.frames.sharetweet.ShareTweetFrameListener;
import model.Tweet;

import java.util.EventObject;

public class ShareTweetFrameFXML
{
    private final ShareTweetFrameListener listener = new ShareTweetFrameListener();
    public TextField usernameTextField;
    public TextField groupTextField;
    public Button sendButton;

    private Tweet tweet;

    public void setTweet(Tweet tweet)
    {
        this.tweet = tweet;
    }

    public void send(ActionEvent actionEvent)
    {
        String usernames = usernameTextField.getText();
        String groups = groupTextField.getText();
        listener.eventOccurred(new EventObject(sendButton), tweet, usernames, groups);
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
    }
}
