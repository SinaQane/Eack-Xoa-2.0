package apps.mainpage.pages.timeline_bookmarks.view;

        import javafx.fxml.FXMLLoader;
        import javafx.scene.layout.Pane;

        import java.io.IOException;
        import java.util.Objects;

public class EmptyBookmarkPane
{
    private Pane emptyPane;
    private final FXMLLoader loader;

    public EmptyBookmarkPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../../graphic/mainpage/pages/timeline_bookmarks/EmptyBookmarkPane.fxml")));
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
