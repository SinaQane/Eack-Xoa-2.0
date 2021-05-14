package view.pages.explore;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class ExplorePane
{
    private static final String EXPLORE = Config.getConfig("paths").getProperty(String.class, "explore");

    private Pane pane;
    private final FXMLLoader loader;

    public ExplorePane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(EXPLORE)));
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

    public void refresh()
    {
        ExplorePaneFXML explorePaneFXML = this.loader.getController();
        RandomTweetsPane randomTweetsPane = new RandomTweetsPane();
        randomTweetsPane.refresh();
        explorePaneFXML.setExplorePane(randomTweetsPane.getPane());
    }
}
