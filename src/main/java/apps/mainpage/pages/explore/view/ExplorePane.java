package apps.mainpage.pages.explore.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import utils.Config;

import java.io.IOException;
import java.util.Objects;

public class ExplorePane
{
    private static final String EXPLORE = Config.getConfig("paths").getProperty(String.class, "explore");

    private Pane explorePane;
    private final FXMLLoader loader;

    public ExplorePane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(EXPLORE)));
        try
        {
            explorePane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getPane()
    {
        return this.explorePane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public void refresh()
    {
        ExplorePaneFXML fxmlController = this.loader.getController();
        RandomTweetsPane randomTweetsPane = new RandomTweetsPane();
        randomTweetsPane.refresh();
        fxmlController.setExplorePane(randomTweetsPane.getPane());
    }
}
