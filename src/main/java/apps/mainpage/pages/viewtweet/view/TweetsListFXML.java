package apps.mainpage.pages.viewtweet.view;

import apps.mainpage.pages.viewtweet.event.TweetsListForm;
import apps.mainpage.pages.viewtweet.event.TweetsListEvent;
import apps.mainpage.pages.viewtweet.listener.TweetsListListener;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import models.Tweet;

public class TweetsListFXML
{
    private TweetsListListener listener;

    private Tweet tweet;
    private int page;

    public Pane tweetPane;
    public Pane commentPane1;
    public Pane commentPane2;

    public Button previousButton;
    public Button nextButton;

    public void setListener(TweetsListListener listener)
    {
        this.listener = listener;
    }

    public void setTweet(Tweet tweet)
    {
        this.tweet = tweet;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public void setTweetPane(Pane tweetPane)
    {
        this.tweetPane.getChildren().clear();
        this.tweetPane.getChildren().add(tweetPane);
    }

    public void setCommentPane1(Pane commentPane1)
    {
        this.commentPane1.getChildren().clear();
        this.commentPane1.getChildren().add(commentPane1);
    }

    public void setCommentPane2(Pane commentPane2)
    {
        this.commentPane2.getChildren().clear();
        this.commentPane2.getChildren().add(commentPane2);
    }

    public Button getPreviousButton()
    {
        return previousButton;
    }

    public Button getNextButton()
    {
        return nextButton;
    }

    public void previous()
    {
        TweetsListForm pageEvent = new TweetsListForm(this.tweet, this.page);
        listener.eventOccurred(new TweetsListEvent(previousButton, pageEvent));
    }

    public void next()
    {
        TweetsListForm pageEvent = new TweetsListForm(this.tweet, this.page);
        listener.eventOccurred(new TweetsListEvent(nextButton, pageEvent));
    }
}
