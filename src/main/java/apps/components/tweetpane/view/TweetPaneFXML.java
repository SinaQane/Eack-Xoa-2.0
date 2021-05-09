package apps.components.tweetpane.view;

import apps.mainpage.logic.MainPageAgent;
import apps.components.tweetpane.listener.TweetPaneListener;
import db.TweetDB;
import db.UserDB;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import models.User;

public class TweetPaneFXML
{
    private TweetPaneListener listener;

    public Text retweetText;
    public Text usernameText;
    public Text tweetText;
    public Button viewImageButton;
    public Button upvoteButton;
    public Button downvoteButton;
    public Button retweetButton;
    public Button saveButton;
    public Button sendButton;
    public Button reportButton;
    public Button viewTweetButton;
    public Button commentButton;
    public Button viewUserButton;

    public void setListener(TweetPaneListener listener)
    {
        this.listener = listener;
    }

    public void setTweetPane(String[] tweetsId)
    {
        if (tweetsId[1].equals("0"))
        {
            this.retweetText.setVisible(false);
        }
        else
        {
            this.retweetText.setVisible(true);
            this.retweetText.setText("retweeted by " + UserDB.getUserDB().get(tweetsId[1]).getUsername());
        }

        long ownerId = TweetDB.getTweetDB().get(tweetsId[0]).getOwner();
        this.usernameText.setText("@" + UserDB.getUserDB().get(ownerId).getUsername());

        this.tweetText.setText(TweetDB.getTweetDB().get(tweetsId[0]).getText());

        // TODO set picture
    }

    public void viewImage()
    {
        listener.eventOccurred(viewImageButton);
    }

    public void upvote()
    {
        listener.eventOccurred(upvoteButton);
    }

    public void downvote()
    {
        listener.eventOccurred(downvoteButton);
    }

    public void retweet()
    {
        listener.eventOccurred(retweetButton);
    }

    public void save()
    {
        listener.eventOccurred(saveButton);
    }

    public void send()
    {
        listener.eventOccurred(sendButton);
    }

    public void report()
    {
        listener.eventOccurred(reportButton);
    }

    public void viewTweet()
    {
        listener.eventOccurred(viewTweetButton);
    }

    public void comment()
    {
        listener.eventOccurred(commentButton);
    }

    public void viewUser()
    {
        listener.eventOccurred(viewUserButton);
    }
}
