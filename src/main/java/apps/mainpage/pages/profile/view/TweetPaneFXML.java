package apps.mainpage.pages.profile.view;

import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.pages.profile.event.ProfileEvent;
import apps.mainpage.pages.profile.listener.ProfileListener;
import db.TweetDB;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import models.User;

public class TweetPaneFXML
{
    private ProfileListener listener;

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

    public void setListener(ProfileListener listener)
    {
        this.listener = listener;
    }

    public void setTweetPane(String[] tweetsId)
    {
        User user = MainPageAgent.getMainPageAgent().getUser();

        if (tweetsId[1].equals("0"))
        {
            this.retweetText.setVisible(true);
            this.retweetText.setText("retweeted by " + user.getUsername());
        }
        else
        {
            this.retweetText.setVisible(false);
        }

        this.usernameText.setText("@" + user.getUsername());

        this.tweetText.setText(TweetDB.getTweetDB().get(tweetsId[0]).getText());

        // TODO set picture
    }

    public void viewImage()
    {
        listener.eventOccurred(new ProfileEvent(viewImageButton));
    }

    public void upvote()
    {
        listener.eventOccurred(new ProfileEvent(upvoteButton));
    }

    public void downvote()
    {
        listener.eventOccurred(new ProfileEvent(downvoteButton));
    }

    public void retweet()
    {
        listener.eventOccurred(new ProfileEvent(retweetButton));
    }

    public void save()
    {
        listener.eventOccurred(new ProfileEvent(saveButton));
    }

    public void send()
    {
        listener.eventOccurred(new ProfileEvent(sendButton));
    }

    public void report()
    {
        listener.eventOccurred(new ProfileEvent(reportButton));
    }

    public void viewTweet()
    {
        listener.eventOccurred(new ProfileEvent(viewTweetButton));
    }

    public void comment()
    {
        listener.eventOccurred(new ProfileEvent(commentButton));
    }

    public void viewUser()
    {
        listener.eventOccurred(new ProfileEvent(viewUserButton));
    }
}
