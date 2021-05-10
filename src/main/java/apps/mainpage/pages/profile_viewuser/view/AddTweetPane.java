package apps.mainpage.pages.profile_viewuser.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class AddTweetPane
{
    private Pane newTweetsPane;
    private final FXMLLoader loader;

    public AddTweetPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../../graphic/mainpage/pages/profile_viewuser/AddTweet.fxml")));
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
