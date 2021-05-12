package apps.mainpage.pages.timeline_bookmarks.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import utils.Config;

import java.io.IOException;
import java.util.Objects;

public class EmptyTimelinePane
{
    private static final String EMPTY_TIMELINE = Config.getConfig("paths").getProperty(String.class, "emptyTimeline");

    private Pane emptyPane;
    private final FXMLLoader loader;

    public EmptyTimelinePane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(EMPTY_TIMELINE)));
        try
        {
            emptyPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getEmptyPane()
    {
        return this.emptyPane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }
}
