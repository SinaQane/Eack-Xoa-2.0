package apps.components.tweetpane.view;

import apps.components.tweetpane.event.TweetPaneEvent;
import apps.components.tweetpane.listener.TweetPaneListener;
import db.TweetDB;
import db.UserDB;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import models.Tweet;

public class TweetPaneFXML
{
    private TweetPaneListener listener;

    private String tweetId;
    private Long ownerId;

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
        Tweet tweet = TweetDB.getTweetDB().get(tweetsId[0]);

        this.tweetId = tweet.getId();
        this.ownerId = tweet.getOwner();

        if (tweetsId[1].equals("0"))
        {
            this.retweetText.setVisible(false);
        }
        else
        {
            this.retweetText.setVisible(true);
            this.retweetText.setText("retweeted by " + UserDB.getUserDB().get(Long.parseLong(tweetsId[1])).getUsername());
        }

        this.usernameText.setText("@" + UserDB.getUserDB().get(this.ownerId).getUsername());

        this.tweetText.setText(tweet.getText());

        this.upvoteButton.setText("Upvote (" + tweet.getUpvotes().size() + ")");
        this.downvoteButton.setText("Downvote (" + tweet.getDownvotes().size() + ")");
        this.retweetButton.setText("Retweet (" + tweet.getRetweets().size() + ")");
    }

    public Long getOwnerId()
    {
        return ownerId;
    }

    public String getTweetId()
    {
        return tweetId;
    }

    public void refreshButtons()
    {
        Tweet tweet = TweetDB.getTweetDB().get(this.tweetId);

        this.upvoteButton.setText("Upvote (" + tweet.getUpvotes().size() + ")");
        this.downvoteButton.setText("Downvote (" + tweet.getDownvotes().size() + ")");
        this.retweetButton.setText("Retweet (" + tweet.getRetweets().size() + ")");
    }

    public void viewImage()
    {
        listener.eventOccurred(new TweetPaneEvent(viewImageButton));
    }

    public void upvote()
    {
        listener.eventOccurred(new TweetPaneEvent(upvoteButton));
    }

    public void downvote()
    {
        listener.eventOccurred(new TweetPaneEvent(downvoteButton));
    }

    public void retweet()
    {
        listener.eventOccurred(new TweetPaneEvent(retweetButton));
    }

    public void save()
    {
        listener.eventOccurred(new TweetPaneEvent(saveButton));
    }

    public void send()
    {
        listener.eventOccurred(new TweetPaneEvent(sendButton));
    }

    public void report()
    {
        listener.eventOccurred(new TweetPaneEvent(reportButton));
    }

    public void viewTweet()
    {
        listener.eventOccurred(new TweetPaneEvent(viewTweetButton));
    }

    public void comment()
    {
        listener.eventOccurred(new TweetPaneEvent(commentButton));
    }

    public void viewUser()
    {
        listener.eventOccurred(new TweetPaneEvent(viewUserButton));
    }
}
