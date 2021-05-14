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

    private Pane listPane;
    private final FXMLLoader loader;
    private final Tweet tweet;

    public TweetsList(Tweet tweet)
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(TWEETS_LIST)));
        try
        {
            listPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        this.tweet = tweet;
    }

    public Pane getListPane()
    {
        return this.listPane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public void refresh(int page)
    {
        TweetsListFXML fxmlController = this.loader.getController();

        fxmlController.setPage(page);
        fxmlController.setTweet(this.tweet);

        TweetsListLogic logicalAgent = new TweetsListLogic(this.tweet);

        List<String> tweets = logicalAgent.getPage(page);

        String[] mainTweet = new String[]{this.tweet.getId(), "0"};
        TweetPane mainTweetPane = new TweetPane();
        TweetPaneFXML mainTweetFXML = mainTweetPane.getLoader().getController();
        mainTweetFXML.setTweetPane(mainTweet);
        mainTweetFXML.setListener(new TweetPaneListener(mainTweetPane));
        fxmlController.setTweetPane(mainTweetPane.getTweetPane());

        if (tweets.get(0).equals("null"))
        {
            fxmlController.setCommentPane1(new EmptyTweetPane().getTweetPane());
        }
        else
        {
            String[] firstComment = new String[]{tweets.get(0), "0"};
            TweetPane firstCommentPane = new TweetPane();
            TweetPaneFXML firstCommentFXML = firstCommentPane.getLoader().getController();
            firstCommentFXML.setTweetPane(firstComment);
            firstCommentFXML.setListener(new TweetPaneListener(firstCommentPane));
            fxmlController.setCommentPane1(firstCommentPane.getTweetPane());
        }

        if (tweets.get(1).equals("null"))
        {
            fxmlController.setCommentPane2(new EmptyTweetPane().getTweetPane());
        }
        else
        {
            String[] secondComment = new String[]{tweets.get(1), "0"};
            TweetPane secondCommentPane = new TweetPane();
            TweetPaneFXML secondCommentFXML = secondCommentPane.getLoader().getController();
            secondCommentFXML.setTweetPane(secondComment);
            secondCommentFXML.setListener(new TweetPaneListener(secondCommentPane));
            fxmlController.setCommentPane2(secondCommentPane.getTweetPane());
        }

        fxmlController.getPreviousButton().setDisable(!logicalAgent.hasPreviousPage(page));
        fxmlController.getNextButton().setDisable(!logicalAgent.hasNextPage(page));
    }
}
