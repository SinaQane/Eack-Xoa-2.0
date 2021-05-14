package view.components.empty.emptytweet;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class EmptyTweetPane
{
    private static final String EMPTY_TWEET = Config.getConfig("paths").getProperty(String.class, "emptyTweetPane");

    private Pane tweetPane;

    public EmptyTweetPane()
    {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(EMPTY_TWEET)));
        try
        {
            tweetPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getTweetPane()
    {
        return this.tweetPane;
    }
}
