package apps.mainpage.pages.viewtweet.view;

import apps.components.tweetpane.listener.TweetPaneListener;
import apps.components.tweetpane.view.EmptyTweetPane;
import apps.components.tweetpane.view.TweetPane;
import apps.components.tweetpane.view.TweetPaneFXML;
import apps.mainpage.pages.viewtweet.logic.TweetsListAgent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import models.Tweet;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TweetsList
{
    private static final String TWEETS_LIST = "../../../../../graphic/mainpage/tweetslist/TweetsList.fxml"; // TODO config

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

        TweetsListAgent logicalAgent = new TweetsListAgent(this.tweet);

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
