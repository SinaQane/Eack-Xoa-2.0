package view.components.group;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class GroupPane
{
    private static final String GROUP = Config.getConfig("paths").getProperty(String.class, "groupPane");

    private Pane groupPane;
    private final FXMLLoader loader;

    public GroupPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(GROUP)));
        try
        {
            groupPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getGroupPane()
    {
        return this.groupPane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }
}
