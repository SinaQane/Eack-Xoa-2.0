package view.components.empty.emptyuser;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class EmptyUserPane
{
    private static final String EMPTY_USER = Config.getConfig("paths").getProperty(String.class, "emptyUserPane");

    private Pane emptyPane;

    public EmptyUserPane()
    {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(EMPTY_USER)));
        try
        {
            emptyPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getPane()
    {
        return this.emptyPane;
    }
}