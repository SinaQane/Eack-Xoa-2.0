package apps.mainpage.pages.profile.view;

import apps.mainpage.pages.profile.event.PageFormEvent;
import apps.mainpage.pages.profile.event.ProfileEvent;
import apps.mainpage.pages.profile.listener.ProfileListener;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class TweetsPaneFXML
{
    private ProfileListener listener;

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
        PageFormEvent pageEvent = new PageFormEvent(this.page);
        listener.eventOccurred(new ProfileEvent(previousButton, pageEvent));
    }

    public void next()
    {
        PageFormEvent pageEvent = new PageFormEvent(this.page);
        listener.eventOccurred(new ProfileEvent(nextButton, pageEvent));
    }

    public void tweet()
    {
        listener.eventOccurred(new ProfileEvent(tweetButton));
    }
}
