package apps.mainpage.pages.timeline_bookmarks.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class TimelinePane
{
    private Pane timelinePane;
    private final FXMLLoader loader;
    private final String pageKind;

    public TimelinePane(String pageKind)
    {
        this.pageKind = pageKind;

        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../../graphic/mainpage/pages/timeline_bookmarks/Timeline.fxml")));
        try
        {
            timelinePane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getTimelinePane()
    {
        return this.timelinePane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public void refresh(int page)
    {

    }
}
