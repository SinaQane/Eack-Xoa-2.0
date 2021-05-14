package view.components.tweet;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class TweetPane
{
    private static final String TWEET = Config.getConfig("paths").getProperty(String.class, "tweetPane");

    private Pane pane;
    private final FXMLLoader loader;

    public TweetPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(TWEET)));
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

    public FXMLLoader getLoader()
    {
        return this.loader;
    }
}
