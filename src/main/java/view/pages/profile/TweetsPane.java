package view.pages.profile;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class TweetsPane
{
    private static final String TWEETS_PANE = Config.getConfig("paths").getProperty(String.class, "tweetsPane");

    private Pane tweetsPane;
    private final FXMLLoader loader;

    public TweetsPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(TWEETS_PANE)));
        try
        {
            tweetsPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getTweetsPane()
    {
        return this.tweetsPane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }
}
