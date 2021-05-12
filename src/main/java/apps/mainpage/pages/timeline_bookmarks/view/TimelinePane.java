package apps.mainpage.pages.timeline_bookmarks.view;

import apps.components.tweetpane.listener.TweetPaneListener;
import apps.components.tweetpane.view.EmptyTweetPane;
import apps.components.tweetpane.view.TweetPane;
import apps.components.tweetpane.view.TweetPaneFXML;
import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.pages.timeline_bookmarks.listener.TimelineListener;
import apps.mainpage.pages.timeline_bookmarks.logic.TimelineAgent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class TimelinePane
{
    private static final String TIMELINE = "../../../../../graphic/mainpage/timeline_bookmarks/Timeline.fxml"; // TODO config

    private Pane timelinePane;
    private final FXMLLoader loader;
    private final String pageKind;

    public TimelinePane(String pageKind)
    {
        this.pageKind = pageKind;

        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(TIMELINE)));
        try
        {
            timelinePane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getTimelinePane()
    {
        return this.timelinePane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public void refresh(int page)
    {
        TimelineAgent timelineAgent = new TimelineAgent(MainPageAgent.getMainPageAgent().getUser(), this.pageKind);

        TimelinePaneFXML timelineController = this.loader.getController();

        timelineController.setPage(page);
        timelineController.setPageKind(this.pageKind);
        timelineController.setListener(new TimelineListener());
        timelineController.getPreviousButton().setDisable(!timelineAgent.hasPreviousPage(page));
        timelineController.getNextButton().setDisable(!timelineAgent.hasNextPage(page));
        timelineController.getRefreshButton().setDisable(timelineAgent.getNumberOfPages() == 0);

        int numberOfTweets = timelineAgent.numberOfTweets(page);

        if (numberOfTweets == 0)
        {
            timelineController.getMidLine1().setVisible(false);
            timelineController.getMidLine2().setVisible(false);
            timelineController.setFirstTweetPane(new EmptyTweetPane().getTweetPane());
            timelineController.setThirdTweetPane(new EmptyTweetPane().getTweetPane());
            if (this.pageKind.equals("timeline"))
            {
                timelineController.setSecondTweetPane(new EmptyTimelinePane().getEmptyPane());
            }
            else if (this.pageKind.equals("bookmarks"))
            {
                timelineController.setSecondTweetPane(new EmptyBookmarkPane().getEmptyPane());
            }
        }
        else
        {
            timelineController.getMidLine1().setVisible(true);

            TweetPane firstTweetPane = new TweetPane();
            TweetPaneFXML firstTweetFXML = firstTweetPane.getLoader().getController();
            firstTweetFXML.setListener(new TweetPaneListener(firstTweetPane));
            firstTweetFXML.setTweetPane(timelineAgent.getPage(page).get(0));
            timelineController.setFirstTweetPane(firstTweetPane.getTweetPane());

            if (numberOfTweets == 1)
            {
                timelineController.getMidLine2().setVisible(false);
                timelineController.setSecondTweetPane(new EmptyTweetPane().getTweetPane());
                timelineController.setThirdTweetPane(new EmptyTweetPane().getTweetPane());
            }
            else
            {
                timelineController.getMidLine2().setVisible(true);

                TweetPane secondTweetPane = new TweetPane();
                TweetPaneFXML secondTweetFXML = secondTweetPane.getLoader().getController();
                secondTweetFXML.setListener(new TweetPaneListener(secondTweetPane));
                secondTweetFXML.setTweetPane(timelineAgent.getPage(page).get(1));
                timelineController.setSecondTweetPane(secondTweetPane.getTweetPane());

                if (numberOfTweets == 2)
                {
                    timelineController.setThirdTweetPane(new EmptyTweetPane().getTweetPane());
                }
                else
                {
                    TweetPane thirdTweetPane = new TweetPane();
                    TweetPaneFXML thirdTweetFXML = thirdTweetPane.getLoader().getController();
                    thirdTweetFXML.setListener(new TweetPaneListener(thirdTweetPane));
                    thirdTweetFXML.setTweetPane(timelineAgent.getPage(page).get(2));
                    timelineController.setThirdTweetPane(thirdTweetPane.getTweetPane());
                }
            }
        }
    }
}
