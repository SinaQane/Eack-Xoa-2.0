package apps.mainpage.pages.profile_viewuser.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class TweetsPane
{
    private Pane tweetsPane;
    private final FXMLLoader loader;

    public TweetsPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../../graphic/mainpage/profile_viewuser/TweetsPane.fxml")));
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
