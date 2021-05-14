package view.pages.timeline;

import view.pages.empty.EmptyBookmarkPane;
import view.pages.empty.EmptyTimelinePane;
import listener.components.tweet.TweetPaneListener;
import view.components.empty.emptytweet.EmptyTweetPane;
import view.components.tweet.TweetPane;
import view.components.tweet.TweetPaneFXML;
import controller.mainpage.MainPageController;
import controller.pages.timeline.TimelineLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class TimelinePane
{
    private static final String TIMELINE = Config.getConfig("paths").getProperty(String.class, "timeline");

    private Pane pane;
    private final FXMLLoader loader;

    private final String pageKind;

    public TimelinePane(String pageKind)
    {
        this.pageKind = pageKind;

        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(TIMELINE)));
        try
        {
            pane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getPane()
    {
        return this.pane;
    }

    public void refresh(int page)
    {
        TimelineLogic logic = new TimelineLogic(MainPageController.getMainPageController().getUser(), this.pageKind);

        TimelinePaneFXML timelinePaneFXML = this.loader.getController();

        timelinePaneFXML.setPage(page);
        timelinePaneFXML.setPageKind(this.pageKind);
        timelinePaneFXML.getPreviousButton().setDisable(!logic.hasPreviousPage(page));
        timelinePaneFXML.getNextButton().setDisable(!logic.hasNextPage(page));
        timelinePaneFXML.getRefreshButton().setDisable(logic.getNumberOfPages() == 0);

        int numberOfTweets = logic.numberOfTweets(page);

        if (numberOfTweets == 0)
        {
            timelinePaneFXML.getMidLine1().setVisible(false);
            timelinePaneFXML.getMidLine2().setVisible(false);
            timelinePaneFXML.setFirstTweetPane(new EmptyTweetPane().getTweetPane());
            timelinePaneFXML.setThirdTweetPane(new EmptyTweetPane().getTweetPane());
            if (this.pageKind.equals("timeline"))
            {
                timelinePaneFXML.setSecondTweetPane(new EmptyTimelinePane().getPane());
            }
            else if (this.pageKind.equals("bookmarks"))
            {
                timelinePaneFXML.setSecondTweetPane(new EmptyBookmarkPane().getPane());
            }
        }
        else
        {
            timelinePaneFXML.getMidLine1().setVisible(true);

            TweetPane firstTweetPane = new TweetPane();
            TweetPaneFXML firstTweetFXML = firstTweetPane.getLoader().getController();
            firstTweetFXML.setListener(new TweetPaneListener(firstTweetPane));
            firstTweetFXML.setTweetPane(logic.getPage(page).get(0));
            timelinePaneFXML.setFirstTweetPane(firstTweetPane.getPane());

            if (numberOfTweets == 1)
            {
                timelinePaneFXML.getMidLine2().setVisible(false);
                timelinePaneFXML.setSecondTweetPane(new EmptyTweetPane().getTweetPane());
                timelinePaneFXML.setThirdTweetPane(new EmptyTweetPane().getTweetPane());
            }
            else
            {
                timelinePaneFXML.getMidLine2().setVisible(true);

                TweetPane secondTweetPane = new TweetPane();
                TweetPaneFXML secondTweetFXML = secondTweetPane.getLoader().getController();
                secondTweetFXML.setListener(new TweetPaneListener(secondTweetPane));
                secondTweetFXML.setTweetPane(logic.getPage(page).get(1));
                timelinePaneFXML.setSecondTweetPane(secondTweetPane.getPane());

                if (numberOfTweets == 2)
                {
                    timelinePaneFXML.setThirdTweetPane(new EmptyTweetPane().getTweetPane());
                }
                else
                {
                    TweetPane thirdTweetPane = new TweetPane();
                    TweetPaneFXML thirdTweetFXML = thirdTweetPane.getLoader().getController();
                    thirdTweetFXML.setListener(new TweetPaneListener(thirdTweetPane));
                    thirdTweetFXML.setTweetPane(logic.getPage(page).get(2));
                    timelinePaneFXML.setThirdTweetPane(thirdTweetPane.getPane());
                }
            }
        }
    }
}
