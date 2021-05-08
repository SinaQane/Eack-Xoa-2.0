package apps.mainpage.pages.profile.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class TweetPane
{
    private Pane tweetPane;
    private final FXMLLoader loader;

    public TweetPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../../graphic/mainpage/pages/profile/TweetPane.fxml")));
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
