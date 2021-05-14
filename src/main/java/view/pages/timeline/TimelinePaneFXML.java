package view.pages.timeline;

import listener.pages.timeline.TimelineEvent;
import listener.pages.timeline.TimelineListener;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class TimelinePaneFXML
{
    private final TimelineListener listener = new TimelineListener();

    private int page;
    private String pageKind;

    public Line midLine1;
    public Line midLine2;

    public Pane firstTweetPane;
    public Pane secondTweetPane;
    public Pane thirdTweetPane;

    public Button previousButton;
    public Button nextButton;
    public Button refreshButton;

    public void setPage(int page)
    {
        this.page = page;
    }

    public void setPageKind(String kind)
    {
        this.pageKind = kind;
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

    public void setThirdTweetPane(Pane thirdTweetPane)
    {
        this.thirdTweetPane.getChildren().clear();
        this.thirdTweetPane.getChildren().add(thirdTweetPane);
    }

    public Line getMidLine1()
    {
        return midLine1;
    }

    public Line getMidLine2()
    {
        return midLine2;
    }

    public Button getPreviousButton()
    {
        return previousButton;
    }

    public Button getNextButton()
    {
        return nextButton;
    }

    public Button getRefreshButton()
    {
        return refreshButton;
    }

    public void previous()
    {
        listener.eventOccurred(new TimelineEvent(previousButton, pageKind, page));
    }

    public void next()
    {
        listener.eventOccurred(new TimelineEvent(nextButton, pageKind, page));
    }

    public void refresh()
    {
        listener.eventOccurred(new TimelineEvent(refreshButton, pageKind, page));
    }
}
