package view.pages.timeline;

import view.pages.empty.EmptyBookmarkPane;
import view.pages.empty.EmptyTimelinePane;
import listener.components.tweet.TweetPaneListener;
import view.components.empty.emptytweet.EmptyTweetPane;
import view.components.tweet.TweetPane;
import view.components.tweet.TweetPaneFXML;
import controller.mainpage.MainPageController;
import listener.pages.timeline.TimelineListener;
import controller.pages.timeline.TimelineLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import util.Config;

import java.io.IOException;
import java.util.Objects;

public class TimelinePane
{
    private static final String TIMELINE = Config.getConfig("paths").getProperty(String.class, "timeline");

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
        TimelineLogic timelineLogic = new TimelineLogic(MainPageController.getMainPageAgent().getUser(), this.pageKind);

        TimelinePaneFXML timelineController = this.loader.getController();

        timelineController.setPage(page);
        timelineController.setPageKind(this.pageKind);
        timelineController.setListener(new TimelineListener());
        timelineController.getPreviousButton().setDisable(!timelineLogic.hasPreviousPage(page));
        timelineController.getNextButton().setDisable(!timelineLogic.hasNextPage(page));
        timelineController.getRefreshButton().setDisable(timelineLogic.getNumberOfPages() == 0);

        int numberOfTweets = timelineLogic.numberOfTweets(page);

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
            firstTweetFXML.setTweetPane(timelineLogic.getPage(page).get(0));
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
                secondTweetFXML.setTweetPane(timelineLogic.getPage(page).get(1));
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
                    thirdTweetFXML.setTweetPane(timelineLogic.getPage(page).get(2));
                    timelineController.setThirdTweetPane(thirdTweetPane.getTweetPane());
                }
            }
        }
    }
}
