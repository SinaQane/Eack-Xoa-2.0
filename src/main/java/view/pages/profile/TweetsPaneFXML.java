package view.pages.profile;

import listener.pages.profile.ProfileForm;
import listener.pages.profile.ProfileEvent;
import listener.pages.profile.ProfileListener;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import model.User;

public class TweetsPaneFXML
{
    private ProfileListener listener;

    private User user;
    private int page;

    public Pane tweetsPane;
    public Button previousButton;
    public Button nextButton;
    public Button tweetButton;
    public Pane firstTweetPane;
    public Pane secondTweetPane;

    public Text noTweetsText;
    public Line midLine;

    public void setListener(ProfileListener listener)
    {
        this.listener = listener;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public void setFirstTweetPane(Pane firstTweetPane)
    {
        this.firstTweetPane.getChildren().clear();
        this.firstTweetPane.getChildren().add(firstTweetPane);
    }

    public void setSecondTweetPane(Pane secondTweetPane)
    {
        this.secondTweetPane.getChildren().clear();
        this.secondTweetPane.getChildren().add(secondTweetPane);
    }

    public Text getNoTweetsText()
    {
        return this.noTweetsText;
    }

    public Line getMidLine()
    {
        return this.midLine;
    }

    public Button getPreviousButton()
    {
        return this.previousButton;
    }

    public Button getNextButton()
    {
        return this.nextButton;
    }

    public void previous()
    {
        ProfileForm profileForm = new ProfileForm(this.user, this.page);
        listener.eventOccurred(new ProfileEvent(previousButton, profileForm));
    }

    public void next()
    {
        ProfileForm profileForm = new ProfileForm(this.user, this.page);
        listener.eventOccurred(new ProfileEvent(nextButton, profileForm));
    }

    public void tweet()
    {
        ProfileForm profileForm = new ProfileForm(this.user, this.page);
        listener.eventOccurred(new ProfileEvent(tweetButton, profileForm));
    }
}
