package apps.components.notification.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import utils.Config;

import java.io.IOException;
import java.util.Objects;

public class NotificationPane
{
    private static final String NOTIFICATION_PANE = Config.getConfig("paths").getProperty(String.class, "notifications");

    private Pane notificationPane;
    private final FXMLLoader loader;

    public NotificationPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(NOTIFICATION_PANE)));
        try
        {
            notificationPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public Pane getNotificationPane()
    {
            return this.notificationPane;
    }
}
