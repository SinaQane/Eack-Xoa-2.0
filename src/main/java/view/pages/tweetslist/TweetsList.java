package view.pages.tweetslist;

import listener.components.tweet.TweetPaneListener;
import view.components.empty.emptytweet.EmptyTweetPane;
import view.components.tweet.TweetPane;
import view.components.tweet.TweetPaneFXML;
import controller.pages.tweetslist.TweetsListLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.Tweet;
import config.Config;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TweetsList
{
    private static final String TWEETS_LIST = Config.getConfig("paths").getProperty(String.class, "tweetsList");

    private Pane pane;
    private final FXMLLoader loader;

    private final Tweet tweet;

    public TweetsList(Tweet tweet)
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(TWEETS_LIST)));
        try
        {
            pane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        this.tweet = tweet;
    }

    public Pane getPane()
    {
        return this.pane;
    }

    public void refresh(int page)
    {
        TweetsListFXML tweetsListFXML = this.loader.getController();

        tweetsListFXML.setPage(page);
        tweetsListFXML.setTweet(this.tweet);

        TweetsListLogic logic = new TweetsListLogic(this.tweet);

        List<String> tweets = logic.getPage(page);

        String[] mainTweet = new String[]{this.tweet.getId(), "0"};
        TweetPane mainTweetPane = new TweetPane();
        TweetPaneFXML mainTweetFXML = mainTweetPane.getLoader().getController();
        mainTweetFXML.setTweetPane(mainTweet);
        mainTweetFXML.setListener(new TweetPaneListener(mainTweetPane));
        tweetsListFXML.setTweetPane(mainTweetPane.getPane());

        if (tweets.get(0).equals("null"))
        {
            tweetsListFXML.setCommentPane1(new EmptyTweetPane().getTweetPane());
        }
        else
        {
            String[] firstComment = new String[]{tweets.get(0), "0"};
            TweetPane firstCommentPane = new TweetPane();
            TweetPaneFXML firstCommentFXML = firstCommentPane.getLoader().getController();
            firstCommentFXML.setTweetPane(firstComment);
            firstCommentFXML.setListener(new TweetPaneListener(firstCommentPane));
            tweetsListFXML.setCommentPane1(firstCommentPane.getPane());
        }

        if (tweets.get(1).equals("null"))
        {
            tweetsListFXML.setCommentPane2(new EmptyTweetPane().getTweetPane());
        }
        else
        {
            String[] secondComment = new String[]{tweets.get(1), "0"};
            TweetPane secondCommentPane = new TweetPane();
            TweetPaneFXML secondCommentFXML = secondCommentPane.getLoader().getController();
            secondCommentFXML.setTweetPane(secondComment);
            secondCommentFXML.setListener(new TweetPaneListener(secondCommentPane));
            tweetsListFXML.setCommentPane2(secondCommentPane.getPane());
        }

        tweetsListFXML.getPreviousButton().setDisable(!logic.hasPreviousPage(page));
        tweetsListFXML.getNextButton().setDisable(!logic.hasNextPage(page));
    }
}
