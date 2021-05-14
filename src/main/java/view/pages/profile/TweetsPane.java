package view.pages.profile;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class TweetsPane
{
    private static final String TWEETS_PANE = Config.getConfig("paths").getProperty(String.class, "tweetsPane");

    private Pane pane;
    private final FXMLLoader loader;

    public TweetsPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(TWEETS_PANE)));
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
