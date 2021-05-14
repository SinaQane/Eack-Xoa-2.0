package apps.components.emptypanes.emptypane;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import utils.Config;

import java.io.IOException;
import java.util.Objects;

public class EmptyPane
{
    private static final String EMPTY_USER = Config.getConfig("paths").getProperty(String.class, "emptyPane");

    private Pane emptyPane;

    public EmptyPane()
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