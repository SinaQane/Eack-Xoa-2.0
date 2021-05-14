package view.components.user;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class UserPane
{
    private static final String USER = Config.getConfig("paths").getProperty(String.class, "userPane");

    private Pane pane;
    private final FXMLLoader loader;

    public UserPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(USER)));
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
