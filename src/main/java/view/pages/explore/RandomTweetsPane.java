package view.pages.explore;

import listener.components.tweet.TweetPaneListener;
import view.components.empty.emptytweet.EmptyTweetPane;
import view.components.tweet.TweetPane;
import view.components.tweet.TweetPaneFXML;
import controller.pages.explore.RandomTweetsLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import util.Config;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class RandomTweetsPane
{
    private static final String RANDOM_TWEETS = Config.getConfig("paths").getProperty(String.class, "randomTweets");

    private Pane pane;
    private final FXMLLoader loader;

    public RandomTweetsPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(RANDOM_TWEETS)));
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

    public void refresh()
    {
        RandomTweetsLogic logicalAgent = new RandomTweetsLogic();

        RandomTweetsPaneFXML fxmlController = this.loader.getController();

        List<String> tweets = logicalAgent.getTweets();

        for (int i = 0; i < tweets.size(); i++)
        {
            if (tweets.get(i).equals(""))
            {
                fxmlController.setTweetPane(i, new EmptyTweetPane().getTweetPane());
            }
            else
            {
                TweetPane tweetPane = new TweetPane();
                ((TweetPaneFXML) tweetPane.getLoader().getController()).setListener(new TweetPaneListener(tweetPane));
                ((TweetPaneFXML) tweetPane.getLoader().getController()).setTweetPane(new String[]{tweets.get(i), "0"});
                fxmlController.setTweetPane(i, tweetPane.getTweetPane());
            }
        }
    }
}
