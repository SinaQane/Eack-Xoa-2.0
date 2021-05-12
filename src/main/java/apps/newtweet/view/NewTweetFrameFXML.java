package apps.newtweet.view;

import apps.newtweet.event.NewTweetEvent;
import apps.newtweet.event.NewTweetFormEvent;
import apps.newtweet.listener.NewTweetFrameListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewTweetFrameFXML
{
    private final NewTweetFrameListener listener = new NewTweetFrameListener();

    public TextField tweetTextField;
    public TextField picsPathTextField;
    public Button sendTweetButton;

    private String upperTweet;

    public void setUpperTweet(String upperTweet)
    {
        this.upperTweet = upperTweet;
    }

    public void tweet(ActionEvent event)
    {
        NewTweetFormEvent tweetEvent = new NewTweetFormEvent(this.upperTweet, tweetTextField.getText(), picsPathTextField.getText());
        listener.eventOccurred(new NewTweetEvent(sendTweetButton, tweetEvent));
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
