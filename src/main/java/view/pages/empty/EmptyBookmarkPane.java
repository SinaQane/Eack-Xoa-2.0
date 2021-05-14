package view.pages.empty;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class EmptyBookmarkPane
{
    private static final String EMPTY_BOOKMARK = Config.getConfig("paths").getProperty(String.class, "emptyBookmark");

    private Pane pane;

    public EmptyBookmarkPane()
    {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(EMPTY_BOOKMARK)));
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
}
