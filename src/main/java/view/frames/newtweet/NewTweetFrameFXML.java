package view.frames.newtweet;

import listener.frames.newtweet.NewTweetEvent;
import listener.frames.newtweet.NewTweetForm;
import listener.frames.newtweet.NewTweetFrameListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewTweetFrameFXML
{
    private final NewTweetFrameListener listener = new NewTweetFrameListener();

    private long id = -1;

    public TextField tweetTextField;
    public TextField picsPathTextField;
    public Button sendTweetButton;

    private String upperTweet;

    public void setId(long id)
    {
        this.id = id;
    }

    public void setUpperTweet(String upperTweet)
    {
        this.upperTweet = upperTweet;
    }

    public void tweet(ActionEvent actionEvent)
    {
        NewTweetForm tweetEvent = new NewTweetForm(this.upperTweet, tweetTextField.getText(), picsPathTextField.getText());
        listener.setId(this.id);
        listener.eventOccurred(new NewTweetEvent(sendTweetButton, tweetEvent));
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
    }
}
