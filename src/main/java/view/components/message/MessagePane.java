package view.components.message;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class MessagePane
{
    private static final String MESSAGE = Config.getConfig("paths").getProperty(String.class, "messagePane");

    private Pane messagePane;
    private final FXMLLoader loader;

    public MessagePane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(MESSAGE)));
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

    public FXMLLoader getLoader()
    {
        return this.loader;
    }
}
