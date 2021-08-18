package view.pages.profile;

import config.Config;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class PrivatePane
{
    private Pane pane;

    private static final String PRIVATE_PANE = Config.getConfig("paths").getProperty(String.class, "privatePane");

    public PrivatePane()
    {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(PRIVATE_PANE)));
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
        return pane;
    }
}
