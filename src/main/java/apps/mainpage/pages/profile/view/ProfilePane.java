package apps.mainpage.pages.profile.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class ProfilePane
{
    private Pane profilePane;
    private final FXMLLoader loader;

    public ProfilePane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../../graphic/mainpage/pages/profile/Profile.fxml")));
        try
        {
            profilePane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getProfilePane()
    {
        return profilePane;
    }

    public FXMLLoader getLoader()
    {
        return loader;
    }
}
