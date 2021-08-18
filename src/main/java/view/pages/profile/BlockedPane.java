package view.pages.profile;

import config.Config;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class BlockedPane
{
    private Pane pane;

    private static final String BLOCKED_PANE = Config.getConfig("paths").getProperty(String.class, "blockedPane");

    public BlockedPane()
    {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(BLOCKED_PANE)));
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
