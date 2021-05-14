package view.components.tweet;

import listener.components.tweet.TweetPaneListener;
import db.TweetDB;
import db.UserDB;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Tweet;

import java.util.EventObject;

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
    public Button viewUpperTweetButton;

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

        this.viewUpperTweetButton.setVisible(tweet.getUpperTweet() != null);

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
        listener.eventOccurred(new EventObject(viewImageButton));
    }

    public void upvote()
    {
        listener.eventOccurred(new EventObject(upvoteButton));
    }

    public void downvote()
    {
        listener.eventOccurred(new EventObject(downvoteButton));
    }

    public void retweet()
    {
        listener.eventOccurred(new EventObject(retweetButton));
    }

    public void save()
    {
        listener.eventOccurred(new EventObject(saveButton));
    }

    public void send()
    {
        listener.eventOccurred(new EventObject(sendButton));
    }

    public void report()
    {
        listener.eventOccurred(new EventObject(reportButton));
    }

    public void viewTweet()
    {
        listener.eventOccurred(new EventObject(viewTweetButton));
    }

    public void comment()
    {
        listener.eventOccurred(new EventObject(commentButton));
    }

    public void viewUser()
    {
        listener.eventOccurred(new EventObject(viewUserButton));
    }

    public void viewUpperTweet()
    {
        listener.eventOccurred(new EventObject(viewUpperTweetButton));
    }
}
