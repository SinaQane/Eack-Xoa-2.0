package view.components.user;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import util.Config;

import java.io.IOException;
import java.util.Objects;

public class UserPane
{
    private static final String USER = Config.getConfig("paths").getProperty(String.class, "userPane");

    private Pane userPane;
    private final FXMLLoader loader;

    public UserPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(USER)));
        try
        {
            userPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getUserPane()
    {
        return this.userPane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }
}
