package view.components.notification;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class NotificationPane
{
    private static final String NOTIFICATION_PANE = Config.getConfig("paths").getProperty(String.class, "notification");

    private Pane pane;
    private final FXMLLoader loader;

    public NotificationPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(NOTIFICATION_PANE)));
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
