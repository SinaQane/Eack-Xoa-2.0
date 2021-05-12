package apps.components.tweetpane.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class TweetPane
{
    private static final String TWEET = "../../../../graphic/components/tweetpane/TweetPane.fxml"; // TODO config

    private Pane tweetPane;
    private final FXMLLoader loader;

    public TweetPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(TWEET)));
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

    public FXMLLoader getLoader()
    {
        return this.loader;
    }
}
