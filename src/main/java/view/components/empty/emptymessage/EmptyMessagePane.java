package view.components.empty.emptymessage;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import util.Config;

import java.io.IOException;
import java.util.Objects;

public class EmptyMessagePane
{
    private static final String EMPTY_MESSAGE = Config.getConfig("paths").getProperty(String.class, "emptyMessagePane");

    private Pane messagePane;

    public EmptyMessagePane()
    {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(EMPTY_MESSAGE)));
        try
        {
            messagePane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getMessagePane()
    {
        return this.messagePane;
    }
}
