package apps.mainpage.pages.profile.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class NewTweetPane
{
    private Pane newTweetsPane;
    private final FXMLLoader loader;

    public NewTweetPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../../graphic/mainpage/pages/profile/NewTweet.fxml")));
        try
        {
            newTweetsPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getNewTweetPane()
    {
        return this.newTweetsPane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }
}
