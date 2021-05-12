package apps.components.tweetpane.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class EmptyTweetPane
{
    private static final String EMPTY_TWEET = "../../../../graphic/components/tweetpane/EmptyTweetPane.fxml"; // TODO config

    private Pane tweetPane;
    private final FXMLLoader loader;

    public EmptyTweetPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(EMPTY_TWEET)));
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
